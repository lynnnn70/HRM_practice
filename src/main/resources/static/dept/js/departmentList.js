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
    if(selectedDeptId.val() === null){
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

        let data ={
            deptName: inputDeptName_el.val(),
            deptId: selectedDeptId
        }

        if(control){
            $.ajax({
                url:'http://localhost:8080/api/departmentList',
                method: 'GET',
                contentType: JSON.stringify(data),
                success: function (response){

                }

            })


        }

    })
})