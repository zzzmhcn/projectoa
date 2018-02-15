var vm = new Vue({
    el : '#user_create',
    data : {
        departments : [],
        positions : []
    },
    methods : {
        getPosition : getPosition,
        getDepartment : getDepartment,
        createUser : createUser
    },
    created : getInit()

})

function getInit() {
    getDepartment();
    getPosition();
}

//查询职位
function getPosition() {
    jQuery.ajax({
        type: "POST",
        url: "/position/query",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.positions = result.value;
        }
    });
}

//查询部门
function getDepartment() {
    jQuery.ajax({
        type: "POST",
        url: "/department/query",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.departments = result.value;
        }
    });
}

//创建用户
function createUser() {
    var username = $('#username').val();
    var realname = $('#realname').val();
    var department_id = $('#department').val();
    var position_id = $('#position').val();
    var usernameSize = username.length;
    //用户名正则，4到16位（字母，数字，下划线，减号）
    var uPattern = /^[a-zA-Z0-9_-]{6,20}$/;
    if (!uPattern.test(username)){
        showAlert('用户名必须是数字或者字母,长度为6-20字符之间,请重新输入','您输入的用户名不正确');
    }else if (realname == '' || realname == null){
        showAlert('请输入员工姓名');
    }else{
        //发送创建用户请求
    }
}