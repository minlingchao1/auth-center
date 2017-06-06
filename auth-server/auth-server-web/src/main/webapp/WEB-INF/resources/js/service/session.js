$(function () {
    bootbox.setDefaults("locale","zh_CN");
    $("#sessionUserTable tbody").on("click", ".forceLogOut", Session.forceLogOut);

})

var Session={
    forceLogOut:function () {
        var sessionId=$(this).attr("session");
        bootbox.confirm("确定强制退出该用户?", function(result) {
            if(result){
                ajaxSetup("post","/session/forceLogout","sessionId="+sessionId,Session.callback);
            }
        });
    },

    callback:function (data) {

        if(data.success){
            swalSuccess("修改成功")
        }
        window.location.reload();
    }
}
