//===== 取得元素 =====
let inputDeptName_el = $("#inputDeptName");
const selectedDeptId = $('#deptId_select').val();

//===== 判斷錯誤 =====
function isValid(inputElement){
    if(inputElement.val() === null || inputElement.val().trim() === ""){
        return false;
    }
    return true;
}


function hasSelectValue(){
    if(selectedDeptId === null){
        return false;
    }
    return true;
}

//===== 判斷錯誤處理新增的文字 =====
function showWarnMsg(inputElement , message){
    $("<span>").html(message).css("color", "red").addClass("error_message").insertAfter(inputElement);
}


$(document).ready(function (){

    $('#search_button').on('click', function (e){
        e.preventDefault();
        let control = true;

        $(".error_message").remove();

        if(!isValid(inputDeptName_el) && !hasSelectValue()){
            showWarnMsg(inputDeptName_el, "請填寫部門名稱或選擇部門編號");
            control = false;
        }

        let data = {
            deptName: inputDeptName_el.val(),
            deptId: selectedDeptId
        }

        if(control){
            // 獲取使用者輸入的中文部門名稱
            let chineseDeptName = inputDeptName_el.val();
            // 將中文部門名稱進行編碼
            let encodedDeptName = encodeURIComponent(chineseDeptName);

            $.ajax({
                url:`http://localhost:8080/api/listDepartmentByName/${encodedDeptName}`,
                method: 'GET',
                success: function (response){
                    console.log(response);
                    if(response.status === -1){
                        console.log("查無資料", response);
                        showWarnMsg(inputDeptName_el, "查無此部門資料");
                    }else if(response.status === 1)
                    console.log(response);
                },
                error: function (error){
                    console.log("查詢失敗", error);
                }
            });
        }

    })
})