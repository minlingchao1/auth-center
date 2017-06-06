$(function () {
    getResourceTree();
    getRoleTree()
    $(".distri").click(distriResource)
})

function getResourceTree() {
    $.ajax({
               async:false,
               type:"get",
               url:"/resource/getResourceApp",
               dataType:"json",
               success:function (data) {
                   if(data.result){
                       $.each(data.result,function (i,v) {
                           $("#tree-"+v.appId).jstree({
                              "core": {
                                  "data": v.treeNodeDtos,
                                  "themes":{
                                      "icons":false
                                  }
                              },
                              "plugins" : [ "changed" ,"checkbox"]
                          })
                       })
                   }
               }
           })
}

function getRoleTree() {
    $.ajax({
               async:false,
               type:"get",
               url:"/role/getRoleTree",
               dataType:"json",
               success:function (data) {
                   if(data.result){
                       $.each(data.result,function (i,v) {
                           $("#roletree-"+v.appId).jstree({
                                  "core": {
                                      "data": v.treeNodeDtos,
                                      "themes":{
                                          "icons":false
                                      }
                                  },
                                  "plugins" : [ "changed"],
                              }).on("select_node.jstree",function (e,data) {
                                   var id=data.node.id;
                                   getResourceByRole(id,v.appId);
                              })
                       })
                   }
               }
           })
}

function distriResource() {
    var appId=$(this).attr("appid");


    var role=$("#roletree-"+appId).jstree().get_selected(false);

    if(role==null||role==""){
        swal({
                 title: "请选择角色",
                 confirmButtonColor: "#EF5350",
                 type: "error"
             });
        return;
    }

    var resourcesTree=$("#tree-"+appId).jstree().get_checked(false);
    var resourceIds=$("#tree-"+appId).jstree().get_checked(false);

    $("#tree-"+appId).find(".jstree-undetermined").each(function (i) {
        resourcesTree.push($(this).parent().parent().attr("id")+"_undetermined");
        resourceIds.push($(this).parent().parent().attr("id"));
    });

    $.ajax({
               type:"post",
               url:"/role/modify",
               dataType:"json",
               data:{
                   id:role[0],
                   appId:appId,
                   resourceIds:resourceIds.join(","),
                   resourcesTree:resourcesTree.join(","),
               },
               success:function (data) {
                   if(data.success){
                       swal({
                                title: "修改成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });
                   }else {
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

function getResourceByRole(id,appId) {
    $("#tree-"+appId).jstree("deselect_all");
    $.ajax({
               type:"get",
               url:"/role/getResourceTree",
               dataType:"json",
               data:{
                   id:id,
               },
               success:function (data) {
                   if(data.result){
                       var t=[];
                       var resourceStr=data.result.split(",");
                       $(resourceStr).each(function (i,v) {
                           if(v.indexOf("_undetermined")<=0){
                               t.push("#"+v);
                           }
                       })

                       $("#tree-"+appId).jstree("deselect_all")
                       $("#tree-"+appId).jstree("check_node",t);
                   }
               }
           })
}
