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

//===== 判斷錯誤處理新增的文字 =====
function showWarnMsg(inputElement , message){
    $("<span>").html(message).css("color", "red").addClass("error_message").insertAfter(inputElement);
}



$(document).ready(function(){

    $("#deptSubmitBtn").on('click', function(e) {

        //將form表單預設行為先關閉
        e.preventDefault();
        let control = true;

        //讓錯誤文字只顯示一次
        $(".error_message").remove();

        //按下Btn時先做輸入格式等錯誤處理
        if (!isValid(inputDeptName_el) || !isValid(inputDeptLoc_el)) {
            if (!isValid(inputDeptName_el)) {
                showWarnMsg(inputDeptName_el, "請填寫部門名稱");
            }

            if (!isValid(inputDeptLoc_el)) {
                showWarnMsg(inputDeptLoc_el, "請填寫部門地點");
            }

            control = false;
        }



        //取得表單input資料
        let data = {
            deptName:inputDeptName_el.val(),  //前 物件名稱 : 後 取到的值
            loc:inputDeptLoc_el.val()
        }
        let addurl = 'http://localhost:8080/api/addDepartment';
        if(control){
            //使用fetch發送請求
            //返回一個用於處理非同步操作的Promise對象， 可以代表一個操作的完成或失敗的結果
            //1.
            // fetch(addurl,{
            //     headers:{"Content-Type": "application/json"},
            //     method:'POST',
            //     body: JSON.stringify(data) //data為想傳給伺服器的資料
            // })
            // .then(response=>  response.json())  //將回應轉換為 JSON 格式
            // .then((data)=>{
            //     console.log(data);
            //     if(data.status === -6){
            //         console.log("資料重複");
            //         showWarnMsg(inputDeptName_el, "部門名稱重複");
            //     }else if(data){
            //         console.log("新增成功", data);
            //         Swal.fire({
            //             position: "center",
            //             icon: "success",
            //             title: "部門新增成功!",
            //             showConfirmButton: false,
            //             timer: 1500
            //         });
            //     }else{
            //         console.log("新增失敗");
            //     }
            // })
            // .catch(e =>({
            //     function(error){
            //         //處理錯誤
            //         console.error('資料送出失敗:', error);
            //     }
            // }))

            //2. async/await
            // 如果使用 await，fetch 將會直接返回被解析的 Response 對象(最終結果) 更易讀
            addDept(addurl, data);
        }
        async function addDept(addurl, data){
            try{
                let response = await fetch(addurl,{
                    headers:{"Content-Type": "application/json"},
                    method:'POST',
                    body: JSON.stringify(data) //data為想傳給伺服器的資料
                })
                let resData = await response.json();

                if(resData.status === -6){
                    console.log("資料重複");
                    showWarnMsg(inputDeptName_el, "部門名稱重複");
                }else if(resData){
                    console.log("新增成功", resData);
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
            }catch (e){
                console.error('資料送出失敗:', e);
            }
        }

    });
});