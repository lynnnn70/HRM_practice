//===== 取得元素 =====
let inputDeptName_el = $("#inputDeptName");
let inputDeptLoc_el = $("#inputDeptLoc");

//===== 判斷錯誤 =====
function isValid(inputElement){
    if(inputElement.val() === null || inputElement.val().trim() === ""){
        return false;
    }
    return true;
}

function isValid(inputElement, message){
    if(inputElement.val() === null || inputElement.val().trim() === ""){
        showWarnMsg(inputElement , message)
        return false;
    }
    return true;
}


//===== 判斷錯誤處理新增的文字 =====
function showWarnMsg(inputElement , message){
    $("<span>").html(message).css("color", "red").addClass("error_message").insertAfter(inputElement);
}


//.ready用途:確保HTML檔完全加載並解析後再執行相應的程式碼
$(document).ready(function(){

    $("#deptSubmitBtn").on('click', function(e) {

        //將form表單預設行為先關閉
        e.preventDefault();
        let control = true;

        //讓錯誤文字只顯示一次
        $(".error_message").remove();



        //按下Btn時先做輸入格式等錯誤處理
        if (!isValid(inputDeptName_el,"請填寫部門名稱") ||
            !isValid(inputDeptLoc_el,"請填寫部門地點")) {
            control = false;
        }



        //取得表單input資料
        let data = {
            deptName:inputDeptName_el.val(),  //前 物件名稱 : 後 取到的值
            loc:inputDeptLoc_el.val()
        }

        if(control){
            //使用AJAX發送請求(非同步)
            $.ajax({
                url:'http://localhost:8080/api/addDepartment',
                method:'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response){
                    if(response.status === -6){
                        console.log("資料重複", response);
                        showWarnMsg(inputDeptName_el, "部門名稱重複");
                    }else if(response.status === 1){
                        console.log("新增成功", response);
                        Swal.fire({
                            position: "center",
                            icon: "success",
                            title: "部門新增成功!",
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }else{
                        console.log("新增失敗");
                    }
                },
                error: function(error){
                    //處理錯誤
                    console.error('資料送出失敗:', error);
                }
            });

        }

    });

});