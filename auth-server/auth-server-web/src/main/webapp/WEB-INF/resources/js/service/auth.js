$(function () {
    bootbox.setDefaults("locale","zh_CN");
    $("#confirmAddAuth").click(add);
    $("#confirmModifyAuth").click(confirmModify);
    $("#authTable tbody").on("click", ".modify",modify);
    $("#authTable tbody").on("click", ".del",del);

    getAllUser();
    getAllApp();
})

function add() {
    var userId= $("#add-user").val();
    var appId=$("#add-appInfo").val();
    var roleIds=$("#add-role").val();


    if(userId==null||userId==""){
        swalFail("请选择用户名",null);
    }

    if(appId==null||appId==""){
        swalFail("请选择应用",null);
    }

    if(roleIds==null||roleIds==""){
        swalFail("请选择角色",null);
    }

    ajaxSetup("post","/auth/auth","userId="+userId+"&appId="+appId+"&roleIds="+roleIds,ajaxCallBack);
}

function modify() {
    var userId=$(this).attr("user");
    var appId=$(this).attr("app");
    var roleIds=$(this).attr("role");
    
    $("#modify-user").val(userId).trigger("change");
    $("#modify-appInfo").val(appId).trigger("change");
    $("#modify-role").val(roleIds).trigger("change");
    $("#modify-authId").val($(this).attr("auth"));

    $("#modify-auth").modal("show");
}

function confirmModify() {
    var userId=$("#modify-user").val();
    var appId=$("#modify-appInfo").val();
    var roleIds=$("#modify-role").val();
    var id=$("#modify-authId").val();

    ajaxSetup("post","/auth/modify","id="+id+"&appId="+appId+"&roleIds="+roleIds,ajaxCallBack)
}
function del() {
    var id=$(this).attr("auth");
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            ajaxSetup("post","/auth/delete","id="+id,ajaxCallBack);
        }
    });
}

function getAllUser() {
    $.ajax({
               async: false,
               type: "post",
               url: "/user/select4Auth",
               dataType: "json",
               success: function (data) {
                   if (data.success) {
                       $("#add-user").select2({
                                                  language: 'zh-CN',
                                                  data: data.result,
                                                  placeholder:'请选择用户',
                                              })
                       $("#modify-user").select2({
                                                     language: 'zh-CN',
                                                     data: data.result,
                                                     placeholder:'请选择用户',
                                                 })

                   }
               }
           })
}

function getAllApp () {
    $.ajax({
               async: false,
               type: "post",
               url: "/app/getAppInfo",
               dataType: "json",
               success: function (data) {
                   if (data.success) {
                       $("#add-appInfo").select2({
                                                     language: 'zh-CN',
                                                     data: data.result,
                                                     placeholder:'请选择应用',
                                                 })
                       $("#modify-appInfo").select2({
                                                        language: 'zh-CN',
                                                        data: data.result,
                                                        placeholder:'请选择应用',
                                                    })
                   }
               }
           })

    $("#add-role").select2({
                               placeholder: "请选择角色名称",
                               data:[{id: 0, text: '请选择角色名称'}],
                               tags: true,
                           });
    $("#modify-role").select2({
                                  placeholder: "请选择角色名称",
                                  data:[{id: 0, text: '请选择角色名称'}],
                                  tags: true,
                              });

    $("#add-appInfo").on("change",getRoleByApp)
    $("#modify-appInfo").on("change",getModifyRoleByApp)

}
function getRoleByApp() {
    var appId=$("#add-appInfo").val();

    $("#add-role").select2({
           tags:true,
           ajax: {
               url: "/role/select4Auth",
               dataType: 'json',

               data:function(params){
                   return {
                       appId:appId
                   };
               },
               results: function (data, params) {
                   return{
                       results: data
                   }
               },
               cache: true
           },
       })
    
}

function getModifyRoleByApp() {
    var appId=$("#modify-appInfo").val();

    $.ajax({
               async:false,
               type:"post",
               url:"/role/select4Auth",
               data:{
                   appId:appId
               },
               dataType:"json",
               success:function (data) {
                   $("#modify-role").select2({
                     tags: true,
                     language: 'zh-CN',
                     data: data,
                     placeholder:'请选择应用',
                 })
               }
           })
}