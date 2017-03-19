$(function () {
    bootbox.setDefaults("locale","zh_CN");
    $("#userTable tbody").on("click", ".del", del);
    $("#userTable tbody").on("click", ".lock",lock);
    $("#userTable tbody").on("click", ".unlock",unlock);
})

function del() {
    var id=$(this).attr("user");
    var userTable=$("#userTable");
    bootbox.confirm("确定删除?", function(result) {
        if(result){
           ajaxSetup("post","/user/delete","id="+id,ajaxCallBack);
        }
    });
}




function lock() {
    var id=$(this).attr("user");
    var userTable=$("#userTable");
    bootbox.confirm("确定锁定该用户?", function(result) {
        if(result){
            ajaxSetup("post","/user/lock","id="+id,ajaxCallBack);
        }
    });
}

function unlock() {
    var id=$(this).attr("user");
    var userTable=$("#userTable");
    bootbox.confirm("确定锁定该用户?", function(result) {
        if(result){
            ajaxSetup("post","/user/unlock","id="+id,ajaxCallBack);
        }
    });
}