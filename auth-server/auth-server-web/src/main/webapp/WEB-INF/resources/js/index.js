
$(function () {

    //初始化菜单
    getMenuList();

    //初始化控件
    var app=new App();
    app.init();

    //路由
    $(".subMenu").on("click",function () {
        $("#ifr").attr("src",$(this).find("a").attr("url"));
    })
})



//菜单
function getMenuList() {
    $.ajax({
       async: false,
       type: "get",
       url: "/resource/menu",
       dataType: "json",

       success: function (data) {
           var html=[];
           $.each(data.result,function (i,v) {
               html.push('<li class="headerMenu"><a href="#"><i class="icon-stack"></i><span>'+v.name+'</span></a>');
               html.push('<ul>')
               $.each(v.list,function (j,t) {
                   html.push('<li class="subMenu"><a url="'+t.url+'" href="javascript:;">'+t.name+'</a></li>')
               })
               html.push('</ul></li>')
           })
           $("#menu").append(html.join(''))
       }
   })
}