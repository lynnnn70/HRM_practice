//html預設選單沒選擇會是 查詢全部 value="-1";
function getUrl(baseUrl , selectValue){
    let isSelectAll = false;
    if(selectValue == -1){
        isSelectAll =true;
    }
     return baseUrl + `?id=${selectValue}&selectAll=${isSelectAll}`;
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

                },
                error: function (error){
                    console.log("查詢失敗", error);
                }
            });
        }
    })
})