var vm = new Vue({
    el : '#userinfo',
    data : {
        departments : [],
        positions : [],
        user : {},
        userinfo : {},
    },
    methods : {
        getPosition : getPosition,
        getDepartment : getDepartment,
        getUser : getUser,
        getUserinfo : getUserinfo,
        saveUserinfo : saveUserinfo
    },
    created : getInit()

})

function getInit() {
    getDepartment();
    getPosition();
    getUser();
    getUserinfo();
}

//查询职位
function getPosition() {
    $.ajax({
        type: "POST",
        url: "/projectoa/position/query",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.positions = result.value;
        }
    });
}

//查询部门
function getDepartment() {
    $.ajax({
        type: "POST",
        url: "/projectoa/department/query",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.departments = result.value;
        }
    });
}

//用户回显
function getUser() {
    $.ajax({
        type: "POST",
        url: contextPath + "/userinfo/getSelf",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.user = result.value;
        }
    });

}

//用户信息回显
function getUserinfo() {
    $.ajax({
       type : "POST",
       url : contextPath + "/userinfo/getUserinfo",
       success : function (result) {
            vm.userinfo = result.value;
            $('#birthday').datepicker('setValue', vm.userinfo.birthday);
        }
    });
    
}

function saveUserinfo() {
    var sex = $("input[name='sex']:checked").val();
    var birthday = $('#birthday').val() == '' ? undefined : $('#birthday').val();
    var headImage = $('#hidden').val() == ''?1:$('#hidden').val();
    var age = $('#age').val();
    var identity_card = $('#idcard').val();
    var email = $('#email').val();
    var qq = $('#qq').val();
    var wechat = $('#wechat').val();
    var weibo = $('#weibo').val();
    var phone = $('#phone').val();
    var data = {};
    data.sex = sex;
    data.birthday =birthday==undefined? undefined : new Date(birthday.replace(/-/,"/")) ;
    data.age = age;
    data.identityCard = identity_card;
    data.email = email;
    data.qq = qq;
    data.wechat = wechat;
    data.weibo = weibo;
    data.phone = phone;
    data.headImage = headImage;
    $.ajax({
        type : "POST",
        url : contextPath + "/userinfo/saveUserinfo",
        data: data,
        dataType: "json",
        success : function (result) {
            if (result.code == 000){
                //成功
                showAlert('保存成功');
                setTimeout(function(){
                    window.location.href="/projectoa/userinfo/userinfo"
                },2000);
            }else {
                //失败
                showAlert(result.message);
            }
        }
    });
    
}