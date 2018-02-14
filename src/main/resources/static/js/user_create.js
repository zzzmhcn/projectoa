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
    if (username == '' || username == null){
        alert('请输入用户名');
    }else if (usernameSize < 6 || usernameSize>20){
        alert('用户名长度为6-20字符之间，请重新输入');
    }
    // else if (){
    //     //缺用户名正则判断
    // }
    else if (realname == '' || realname == null){
        alert('请输入员工姓名');
    }else{
        //发送创建用户请求

    }
    
}