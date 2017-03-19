$(function () {

    $("#resourceTable tbody").on("click", ".modify", modify);
    $("#resourceTable tbody").on("click", ".del", del);
    $("#resourceTable tbody").on("click", ".available", modifyAvailable);
    $("#resourceTable tbody").on("click", ".unAvailable", modifyUnAvailable);


    $("#add-resource").click(add)
    $("#confirmAddResource").click(confirmAddResource)
    $("#confirmModifyResource").click(confirmModify);
    $('.select').select2();
    bootbox.setDefaults("locale","zh_CN");
    getAppInfo();

})

function add() {
    if(!checkHasApp()){
        swal({
                 title: "没有创建应用",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    $("#add-resource-modal").modal("show");
}

function confirmAddResource() {
    var resourceName=$("#add-resourceName").val();
    var resourceType=$("#add-resourceType").val();
    var appId=$("#add-appInfo").val();
    var parentId=$("#add-parentResource").val();
    var permission=$("#add-permission").val();
    var url=$("#add-url").val();
    var priority=$("#add-priority").val();


    if(resourceName==""||resourceName==null){
        swal({
                 title: "请输入资源名",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(resourceType==null||resourceType==""){
        swal({
                 title: "请选择资源类型",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(appId==null||appId==""){
        swal({
                 title: "请选择所在应用",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(permission==null||permission==""){
        swal({
                 title: "请选择所需权限",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(resourceType==2&&(url==null||url=="")){
        swal({
                 title: "请选择URL路径",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    $.ajax({
               type:"post",
               url:"/resource/add",
               dataType:"json",
               data:{
                   name:resourceName,
                   type:resourceType,
                   priority:priority,
                   parentId:parentId,
                   permission:permission,
                   url:url,
                   appId:appId
               },
               success:function (data) {
                   if(data.success){
                       $("#add-resource-modal").modal("hide")
                       swal({
                                title: "添加资源成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });

                       //刷新
                       window.location.reload();

                   }else {
                       $("#add-resource-modal").modal("hide")
                       swal({
                                title: "添加资源失败",
                                text:data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function del() {
    var id=$(this).attr("resource");
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/resource/delete",
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
    var resourceId=$(this).attr("resource");
    var resourceName=$(this).attr("resourceName");
    var url=$(this).attr("url");
    var permission=$(this).attr("permission");
    var appInfo=$(this).attr("appInfo");
    var parentId=$(this).attr("parenrid");
    var resourceType=$(this).attr("resourceType");

    $("#resourceId").val(resourceId);
    $("#resourceName").val(resourceName);
    $("#url").val(url);
    $("#permission").val(permission);
    $("#type").val(resourceType).trigger("change");
    $("#appInfo").val(appInfo).trigger("change");



    $("#parentResource").val(parentId).trigger("change");
    $("#modify-resource").modal("show");
}

function confirmModify() {
    var resourceName=$("#resourceName").val();
    var resourceType=$("#type").val();
    var resourceId=$("#resourceId").val();
    var url=$("#url").val();
    var permission=$("#permission").val();
    var parentId=$("#parentResource").val();
    var appId=$("#appInfo").val();

    if(resourceName==""||resourceName==null){
        swal({
                 title: "请输入资源名",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(resourceType==null||resourceType==""){
        swal({
                 title: "请选择资源类型",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(appId==null||appId==""){
        swal({
                 title: "请选择所在应用",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if(permission==null||permission==""){
        swal({
                 title: "请选择所需权限",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    if((resourceType==2||resourceType==3)&&(url==null||url=="")){
        swal({
                 title: "请选择URL路径",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    $.ajax({
               type:"post",
               url:"/resource/modify",
               dataType:"json",
               data:{
                   name:resourceName,
                   type:resourceType,
                   parentId:parentId,
                   permission:permission,
                   url:url,
                   appId:appId,
                   id:resourceId
               },
               success:function (data) {
                   if(data.success){
                       $("#modify-resource").modal("hide")
                       swal({
                                title: "修改资源成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });

                       //刷新
                       window.location.reload();

                   }else {
                       $("#modify-resource").modal("hide")
                       swal({
                                title: "修改资源失败",
                                text:data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function modifyAvailable(){
    var id=$(this).attr("resource");
    bootbox.confirm("确定设置为不可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/resource/setAvailable",
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
    var id=$(this).attr("resource");
    bootbox.confirm("确定设置为不可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/resource/setUnAvailable",
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

function getAppInfo(){
    $.ajax({
               async:false,
               type:"post",
               url:"/app/getAppInfo",
               dataType:"json",
               success:function (data) {
                   if(data.success) {
                       $("#appInfo").select2({
                                                 language: 'zh-CN',
                                                 data: data.result,
                                                 placeholder:'请选择应用',
                                             })
                       $("#add-appInfo").select2({
                                                     language: 'zh-CN',
                                                     data: data.result,
                                                     placeholder:'请选择应用',
                                                 })
                   }
               }
           })


    $("#add-parentResource").select2({
                                         placeholder: "请选择父资源名称",
                                         data:[{id: 0, text: '请选择父资源名称'}]
                                     });
    $("#parentResource").select2({
                                     placeholder: "请选择父资源名称",
                                     data:[{id: 0, text: '请选择父资源名称'}]
                                 });
    $("#add-appInfo").on("change",getAddAllResourceByAppId)
    $("#appInfo").on("change",getModiifyAllResourceByAppId)
}
function getAddAllResourceByAppId(){

    var appId=$("#add-appInfo").val();
    $("#add-parentResource").select2({
                                         ajax: {
                                             url: "/resource/getResourceByAppId",
                                             dataType: 'json',
                                             data:function(params){
                                                 return {
                                                     appId:appId
                                                 };
                                             },
                                             results: function (data, params) {
                                                 return{
                                                     results: data.result
                                                 }
                                             },
                                             cache: true
                                         },
                                     })
}

function getModiifyAllResourceByAppId(){
    var appId=$("#appInfo").val();


    $.ajax({
               async:false,
               type:"post",
               url:"/resource/getResourceByAppId",
               data:{
                   appId:appId
               },
               dataType:"json",
               success:function (data) {
                   if(data.success) {
                       $("#parentResource").select2({
                                                        language: 'zh-CN',
                                                        data: data.result,
                                                        placeholder:'请选择应用',
                                                    })

                   }
               }
           })
}

function checkHasApp () {
    var hasApp=false;
    $.ajax({
               async:false,
               type:"post",
               url:"/app/count",
               dataType:"json",
               success:function (data) {
                   if(data.success){
                       if(data.result>0){
                           hasApp=true;
                       }
                   }
               }
           })
    return hasApp;
}