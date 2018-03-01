var vm_common = new Vue({
    el: '#header',
    data: {
        messageList: [],
        noticeList: [],
    },
    methods: {},
    created: getInit()
})

function getInit() {
    getUnReadMessages();
    getUnReadNotices();
}

//护眼模式
function changeTheme() {
    //这里toggleClass的意思是如果 有就删掉 没有就加上
    //按钮切换
    $('#eyesI').toggleClass('am-icon-toggle-off');
    $('#eyesI').toggleClass('am-icon-toggle-on');
    //主题切换
    $('body').toggleClass('theme-white');
    $('body').toggleClass('theme-black');
    //保存到浏览器缓存 这个暂时不需要 先注着
    saveSelectColor.Color = $('body').attr('class');
    storageSave(saveSelectColor);
}

//退出登录
function exit() {
    sessionStorage.clear();
    window.location.href = contextPath + '/logout';
}

//左边栏的隐藏和出现
function leftSidebar() {
    if ($('.left-sidebar').attr('class').indexOf('active') > 0
        && $('.tpl-content-wrapper').attr('class').indexOf('active') > 0) {
        $('.left-sidebar').addClass('active');
        $('.tpl-content-wrapper').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
        $('.tpl-content-wrapper').removeClass('active');
    }
}


/**
 * 只传一个参数 ，标题不传就用默认标题，需要自定义标题就传入2个参数
 * @param text  内容
 * @param title 标题
 */
function showAlert(text, title) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    $("#alertTitle").html(title);
    $("#alertText").html(text);
    $("#showAlertID").modal();
}

/**
 * 传入只一个参数就是内容 如果传了2个参数那 第二个就是标题
 * 以此类推 第三个是取消的按钮显示的字 第四个是确认按钮显示的字
 * @param text  内容
 * @param title 标题
 * @param cancel    取消
 * @param ok    确认
 * @return 直接返回div的对象，可以连着点modal启用
 *
 * 栗子
 showConfirm('测试','你好','算了','滚蛋').modal({
     onConfirm: function() {
         //干点啥
     },
    onCancel: function() {
         //干点啥
    }
});
 */
function showConfirm(text, title, cancel, ok) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    if (cancel == null || cancel == '' || cancel == undefined) {
        cancel = '取消';
    }
    if (ok == null || ok == '' || ok == undefined) {
        ok = '确定';
    }
    $("#confirmTitle").html(title);
    $("#confirmText").html(text);
    $('#confirmCancel').text(cancel);
    $('#confirmOK').text(ok);
    return $('#showConfirmID');
}


/**
 * 只有第一个参数 text 是必填项，其余都是可选项
 * 直接上栗子
 showPrompt('内容','标题','取消','确定').modal({
      onConfirm: function(e) {
        alert('你输入的是：' + e.data)
      }
    });
 */
function showPrompt(text, title, cancel, ok) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    if (cancel == null || cancel == '' || cancel == undefined) {
        cancel = '取消';
    }
    if (ok == null || ok == '' || ok == undefined) {
        ok = '确定';
    }
    $("#promptTitle").html(title);
    $("#promptText").html(text);
    $('#promptCancel').text(cancel);
    $('#promptOK').text(ok);
    return $('#showPromptID');
}

/**
 * 修改密码
 */
function changePassWord() {
    var oldpassword = $('#oldpassword').val();
    var newpassword = $('#newpassword').val();
    var repeatpassword = $('#repeatpassword').val();
    var uPattern = /^[a-zA-Z0-9_-]{6,20}$/;
    if (!uPattern.test(newpassword)) {
        showAlert('密码必须是数字或者字母,长度为6-20字符之间,请重新输入', '您输入的密码不正确');
    } else if (oldpassword == newpassword) {
        showAlert('新密码不能和旧密码相同', '您输入的密码不正确');

    } else if (newpassword != repeatpassword) {
        showAlert('新密码两次输入不一致，请重新输入', '您输入的密码不正确');
    } else {
        //基本验证通过 进入后台验证
        $.ajax({
            type: "POST",
            url: contextPath + '/userinfo/setNewPassWord',
            data: {
                'oldpassword': oldpassword,
                'newpassword': newpassword,
                'repeatpassword': repeatpassword
            },
            dataType: "json",
            success: function (result) {
                if (result.code == '101') {
                    showAlert('发生了异常 : ' + result.message);
                } else if (result.code == '000') {
                    showAlert('修改密码成功', '成功')
                }
            }
        });
    }
}

/**
 * 这里用来获取本人的未读信息
 */
function getUnReadMessages() {
    $.ajax({
        type: "POST",
        url: contextPath + "/message/getUnReadMessages",
        dataType: "json",
        success: function (result) {
            if (result.code == 000) {
                vm_common.messageList = result.value;
            }
        }
    });
}

function getUnReadNotices() {
    $.ajax({
        type: "POST",
        url: contextPath + "/notice/getUnReadNotices",
        dataType: "json",
        success: function (result) {
            if (result.code == 000) {
                vm_common.noticeList = result.value;
            }
        }
    });
}

/**
 * 由于改后台的未读信息数据 前台要等刷新才能看出效果
 * 这里做一个假的读过就简一的效果
 */
function changenums(spanID) {
    var num = $('#' + spanID).text();
    if (num - 1 == 0) {
        $('#' + spanID).text('')
    } else {
        $('#' + spanID).text(num - 1)
    }
}