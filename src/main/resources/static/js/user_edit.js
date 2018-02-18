var vm = new Vue({
    el : '#user_edit',
    data : {
        departments : [],
        positions : [],
        user : {},
    },
    methods : {
        getPosition : getPosition,
        getDepartment : getDepartment,
        detailUser : detailUser,
    },
    created : getInit()

})

function getInit() {
    getDepartment();
    getPosition();
    detailUser();
}

//查询职位
function getPosition() {
    $.ajax({
        type: "POST",
        url: contextPath + "/position/query",
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
        url: contextPath + "/department/query",
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.departments = result.value;
        }
    });
}

function detailUser() {
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);
    $.ajax({
        type: "POST",
        url: contextPath + "/user/detailUser/" + id,
        //data: {username:$("#username").val(), content:$("#content").val()},
        //dataType: "json",
        success: function(result){
            vm.user = result.value;
        }
    });
    
}

//创建用户
// function createUser() {
//     var username = $('#username').val();
//     var realname = $('#realname').val();
//     var department_id = $('#department').val();
//     var position_id = $('#position').val();
//     var usernameSize = username.length;
//     //用户名正则，4到16位（字母，数字，下划线，减号）
//     var uPattern = /^[a-zA-Z0-9_-]{6,20}$/;
//     if (!uPattern.test(username)){
//         showAlert('用户名必须是数字或者字母,长度为6-20字符之间,请重新输入','您输入的用户名不正确');
//     }else if (realname == '' || realname == null){
//         showAlert('请输入员工姓名');
//     }else{
//         //发送创建用户请求
//         var data = {};
//         data.username = username;
//         data.realname = realname;
//         data.departmentId = department_id;
//         data.positionId = position_id;
//
//         $.ajax({
//             type: "POST",
//             url: "/user/userCreate",
//             data: data,
//             dataType: "json",
//             success: function(result){
//                 if (result.code == 000){
//                     //成功
//                     showAlert('创建成功');
//                 }else {
//                     //失败
//                     showAlert(result.message);
//                 }
//
//             }
//         });
//     }
//
// }