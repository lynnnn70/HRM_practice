//html預設選單沒選擇會是 查詢全部 value="-1";
function getUrl(baseUrl , selectValue){
    let isSelectAll = false;
    if(selectValue == -1){
        isSelectAll =true;
    }
     return baseUrl + `?id=${selectValue}&selectAll=${isSelectAll}`;
}

function generateTable(department , index){
    return `<tr>
        <th scope="row">${index+1}</th>
        <td>${department.deptId}</td>
        <td>${department.deptName}</td>
        <td>${department.loc}</td>
        <td><i className="fa-regular fa-pen-to-square"></i></td>
    </tr>`

}

$(document).ready(function (){

    $('#search_button').on('click', function (e){
        e.preventDefault();
        let control = true;

        $(".error_message").remove();

        const select_el = $('#deptId_select');
        const selectedDeptIdValue = select_el.val();
        // console.log($('#deptId_select').val());
        console.log(selectedDeptIdValue);

        // if(selectedDeptIdValue === null){
        //     showWarnMsg(select_el, "請選擇部門編號");
        //     control = false;
        // }
        let url = 'http://localhost:8080/api/listDepartmentById2';
        // if(selectedDeptIdValue != -1 ){
        //     url = url + "?id=" + selectedDeptIdValue;
        // }else {
        //     url = url + "?selectAll=true";
        // }

        if(control){

            $.ajax({
                url: getUrl(url,selectedDeptIdValue),
                method: 'GET',
                success: function (response){
                    console.log(response);
                    console.log(getUrl(url,selectedDeptIdValue));

                    if(!response){
                        console.log("查無資料", response);
                        showWarnMsg(select_el, "查無此部門資料");
                    }
                    //forEach(callbackFn)
                    //callbackFn 為數組中每個元素執行的函數，該函數被調用時將傳入以下參數element,index,array
                    if(response.length > 0){
                        $("tbody").empty();
                        response.forEach((department , index ) => {
                            let content = generateTable(department,index);
                            $("tbody").append(content);
                        })
                    }

                },
                error: function (error){
                    console.log("查詢失敗", error);
                }
            });
        }
    })
})