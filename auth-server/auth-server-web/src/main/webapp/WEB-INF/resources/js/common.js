function ajaxSetup(type,url,data,callback) {
    $.ajax({
           type:type,
           url:url,
           dataType:"json",
           data:data,
           success:callback
     })
}

function swalSuccess(title) {
    swal({
         title:title,
         confirmButtonColor: "#66BB6A",
         type: "success"
    });
}

function swalFail(title,msg) {
    swal({
             title:title,
             text:msg,
             confirmButtonColor: "#EF5350",
             type: "error"
         });
}

function ajaxCallBack(data) {
    console.log(data)
    if(data.success){
        swalSuccess("修改成功");
    }else {
        swalFail("修改失败",data.msg);
    }
    window.location.reload();
}
