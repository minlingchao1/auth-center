$(function () {

    $("#appTable tbody").on("click", ".modify", modify);
    $("#appTable tbody").on("click", ".del", del);
    $("#appTable tbody").on("click", ".available", modifyAvailable);
    $("#appTable tbody").on("click", ".unAvailable", modifyUnAvailable);

    $("#confirmModifyApp").click(confirmModify)

    $("#confirmAddApp").click(add);

    bootbox.setDefaults("locale","zh_CN");
})

function add() {
    var appName=$("#add-appName").val();
    var descr=$("#add-descr").val();

    if(appName == "" || appName == undefined || appName == null){
        swal({
                 title: "应用名称不能为空",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    $.ajax({
               type:"post",
               url:"/app/add",
               dataType:"json",
               data:{
                   name:appName,
                   descr:descr,
               },
               success:function (data) {
                   if(data.success){
                       $("#add-app-modal").modal("hide");
                       swal({
                                title: "添加应用成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });

                       //刷新
                       window.location.reload();

                   }else {
                       $("#add-app-modal").modal("hide");
                       swal({
                                title: "添加应用失败",
                                text:data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function del() {
    var id=$(this).attr("app");
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/app/delete",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "删除成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "删除失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}

function modify() {
    var id=$(this).attr("app");
    var appName=$(this).attr("appName");
    var descr=$(this).attr("descr");

    $("#appName").val(appName);

    if(descr!=null&&descr!="null"){
        $("#descr").val(descr);
    }

    $("#modify-app").modal("show");
    $("#appId").val(id);
}

function confirmModify() {
    var appName=$("#appName").val();
    var descr=$("#descr").val();
    var appId=$("#appId").val();

    $.ajax({
               type:"post",
               url:"/app/modify",
               dataType:"json",
               data:{
                   id:appId,
                   name:appName,
                   descr:descr
               },
               success:function (data) {
                   if(data.success){
                       swal({
                                title: "修改成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });

                       //刷新
                       $("#modify-app").modal("hide");
                       window.location.reload();
                   }else {
                       $("#modify-app").modal("hide");
                       swal({
                                title: "修改失败",
                                text:data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function modifyAvailable() {
    var id=$(this).attr("app");
    bootbox.confirm("确定设置为可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/app/setAvailable",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "设置成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "设置失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}

function modifyUnAvailable() {
    var id=$(this).attr("app");
    bootbox.confirm("确定设置为不可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/app/setUnAvailable",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "设置成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "设置失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}