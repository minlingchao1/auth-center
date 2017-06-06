$(function () {
    validateMobile();
    validate4Register();
    validate4BindQQ();
    $("#btnSendCode").click(sendVerify);
    $("#bind-btnSendCode").click(sendVerify4BindQQ);


})

var curCount;
var  bindCurCount;
var interObj;
var bindInterObj;

function validateMobile() {
    // 联系电话(手机/电话皆可)验证
    jQuery.validator.addMethod("isPhone", function (value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));
    })
}

function validate4Register() {
    var validator = $("#register").validate({
        ignore: 'input[type=hidden], .select2-input', // ignore
                                                      // hidden
                                                      // fields
        errorClass: 'validation-error-label',
        successClass: 'validation-valid-label',
        highlight: function (element, errorClass) {
            $(element).removeClass(errorClass);
        },
        unhighlight: function (element, errorClass) {
            $(element).removeClass(errorClass);
        },
        errorPlacement: function (error, element) {

            // Styled checkboxes, radios, bootstrap switch
            if (element.parents('div').hasClass("checker")
                || element.parents('div').hasClass("choice")
                || element.parent().hasClass(
                    'bootstrap-switch-container')) {
                if (element.parents('label')
                        .hasClass('checkbox-inline')
                    || element.parents('label')
                        .hasClass('radio-inline')) {
                    error.appendTo(
                        element.parent().parent().parent()
                            .parent());
                }
                else {
                    error.appendTo(
                        element.parent().parent().parent()
                            .parent().parent());
                }
            }

            // Unstyled checkboxes, radios
            else if (element.parents('div')
                         .hasClass('checkbox')
                     || element.parents('div')
                         .hasClass('radio')) {
                error.appendTo(
                    element.parent().parent().parent());
            }

            // Inline checkboxes, radios
            else if (element.parents('label')
                         .hasClass('checkbox-inline')
                     || element.parents('label')
                         .hasClass('radio-inline')) {
                error.appendTo(element.parent().parent());
            }

            // Input group, styled file input
            else if (element.parent().hasClass('uploader')
                     || element.parents()
                         .hasClass('input-group')) {
                error.appendTo(element.parent().parent());
            }
            else {
                error.insertAfter(element);
            }
        },
        validClass: "validation-valid-label",
        success: function (label) {
            label.addClass("validation-valid-label")
                .text("通过")
        },
        rules: {
            vali: "required",
            userName: "required",
            mobile: {
                required: true,
                isPhone: true,

            },
            verify: {
                required: false,
                remote: {
                    url: "/verify/checkVerify",
                    type: "post",
                    data: {
                        mobile: function () {
                            return $("#mobile").val();
                        },
                        code: function () {
                            return $("#verify").val();
                        }
                    }
                }
            },
            password: {
                required: true,
                minlength: 6
            },
            repeat_password: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            }
        },
        messages: {
            userName: "请输入用户名",
            mobile: {
                required: "请输入手机号",
                isPhone: "请输入一个有效的手机号码"
            },
            verify: "请输入验证码",
            password: {
                required: "请输入密码",
                minlength: $.validator.format(
                    "密码不能小于{0}个字 符")
            },
            repeat_password: {
                required: "请输入确认密码",
                minlength: "确认密码不能小于5个字符",
                equalTo: "两次输入密码不一致不一致"
            }
        },
        submitHandler: function (form) {
           register();
        }
    });
}

function validate4BindQQ() {

    var validator = $("#bind-qq").validate({
       ignore: 'input[type=hidden], .select2-input', // ignore
                                                     // hidden
                                                     // fields
       errorClass: 'validation-error-label',
       successClass: 'validation-valid-label',
       highlight: function (element, errorClass) {
           $(element).removeClass(errorClass);
       },
       unhighlight: function (element, errorClass) {
           $(element).removeClass(errorClass);
       },
       errorPlacement: function (error, element) {

           // Styled checkboxes, radios, bootstrap
           // switch
           if (element.parents('div')
                   .hasClass("checker")
               || element.parents('div')
                   .hasClass("choice")
               || element.parent().hasClass(
                   'bootstrap-switch-container')) {
               if (element.parents('label')
                       .hasClass('checkbox-inline')
                   || element.parents('label')
                       .hasClass('radio-inline')) {
                   error.appendTo(
                       element.parent().parent()
                           .parent().parent());
               }
               else {
                   error.appendTo(
                       element.parent().parent()
                           .parent().parent().parent());
               }
           }

           // Unstyled checkboxes, radios
           else if (element.parents('div')
                        .hasClass('checkbox')
                    || element.parents('div')
                        .hasClass('radio')) {
               error.appendTo(
                   element.parent().parent().parent());
           }

           // Inline checkboxes, radios
           else if (element.parents('label')
                        .hasClass('checkbox-inline')
                    || element.parents('label')
                        .hasClass('radio-inline')) {
               error.appendTo(
                   element.parent().parent());
           }

           // Input group, styled file input
           else if (element.parent()
                        .hasClass('uploader')
                    || element.parents()
                        .hasClass('input-group')) {
               error.appendTo(
                   element.parent().parent());
           }
           else {
               error.insertAfter(element);
           }
       },
       validClass: "validation-valid-label",
       success: function (label) {
           label.addClass("validation-valid-label")
               .text("通过")
       },
       rules: {
           vali: "required",
           mobile: {
               required: true,
               isPhone: true,

           },
           verify: {
               required: false,
               remote: {
                   url: "/verify/checkVerify",
                   type: "post",
                   data: {
                       mobile: function () {
                           return $("#bind-mobile")
                               .val();
                       },
                       code: function () {
                           return $("#bind-verify")
                               .val();
                       }
                   }
               }
           },
       },
       messages: {
           mobile: {
               required: "请输入手机号",
               isPhone: "请输入一个有效的手机号码"
           },
           verify: "请输入验证码",
       },
       submitHandler: function (form) {
           bindQQ();
       },
   });
}
function sendVerify() {

    $.ajax({
               type: "post",
               url: "/verify/sendCode",
               dataType: "json",
               data: {
                   mobile: $("#mobile").val()
               },
               success: function (data) {
                   if (data.success) {
                       swal({
                                title: "验证码已发送",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });
                       var count = 60;
                       curCount = count;
                       $("#btnSendCode").attr("disabled", "true");
                       $("#btnSendCode").text(curCount);
                       interObj = window.setInterval(setRemainTimer, 1000); //启动计时器，1秒执行一次
                   } else {
                       $("#modify-user").modal("hide");
                       swal({
                                title: "验证码发送失败",
                                text: data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function sendVerify4BindQQ() {
    $.ajax({
               type: "post",
               url: "/verify/sendCode",
               dataType: "json",
               data: {
                   mobile: $("#bind-mobile").val()
               },
               success: function (data) {
                   if (data.success) {
                       swal({
                                title: "验证码已发送",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });
                       var count = 60;
                       bindCurCount = count;
                       $("#bind-btnSendCode").attr("disabled", "true");
                       $("#bind-btnSendCode").text(bindCurCount);
                       bindInterObj =
                           window.setInterval(setRemainTimer4Bind, 1000); //启动计时器，1秒执行一次
                   } else {
                       swal({
                                title: "验证码发送失败",
                                text: data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function setRemainTimer() {
    if (curCount == 0) {
        window.clearInterval(interObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").text("重新发送验证码");
    }
    else {
        curCount--;
        $("#btnSendCode").text(curCount);
    }
}

function setRemainTimer4Bind() {

    if (bindCurCount == 0) {
        window.clearInterval(bindInterObj);//停止计时器
        $("#bind-btnSendCode").removeAttr("disabled");//启用按钮
        $("#bind-btnSendCode").text("重新发送验证码");
    }
    else {
        console.log(bindCurCount);
        bindCurCount--;
        $("#bind-btnSendCode").text(bindCurCount);
    }
}

function register() {
    var mobile = $("#mobile").val();
    var nick = $("#userName").val();
    var password = $("#password").val();
    var callBackUrl=$("#callBackUrl").val();

    $.ajax({
               type: "post",
               url: "/user/register",
               dataType: "json",
               data: {
                   mobile: $("#mobile").val(),
                   nick: $("#userName").val(),
                   password: $("#password").val(),
                   openId: $("#openId").val()
               },
               success: function (data) {
                   if (data.success) {
                       swal({
                                title: "注册成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });
                       window.location.href = callBackUrl;
                   } else {
                       $("#modify-user").modal("hide");
                       swal({
                                title: "注册失败",
                                text: data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })

}

function bindQQ() {
    var callBackUrl=$("#callBackUrl").val();
    $.ajax({
       type: "post",
       url: "/user/bindQQ",
       dataType: "json",
       data: {
           mobile: $("#bind-mobile").val(),
           openId: $("#openId").val()
       },
       success: function (data) {
           if (data.success) {
               swal({
                        title: "注册成功",
                        confirmButtonColor: "#66BB6A",
                        type: "success"
                    });
               window.location.href =callBackUrl;
           } else {
               $("#modify-user").modal("hide");
               swal({
                        title: "注册失败",
                        text: data.msg,
                        confirmButtonColor: "#EF5350",
                        type: "error"
                    });
           }
       }
   })
}
    