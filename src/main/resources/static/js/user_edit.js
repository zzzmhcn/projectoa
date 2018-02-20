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
        editUser : editUser,
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

//用户回显
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

//修改用户
function editUser() {
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);

    var realname = $('#realname').val();
    var department_id = $('#department').val();
    var position_id = $('#position').val();
    var usernameSize = username.length;
    //用户名正则，4到16位（字母，数字，下划线，减号）
    var uPattern = /^[a-zA-Z0-9_-]{6,20}$/;
    if (realname == '' || realname == null){
        showAlert('请输入员工姓名');
    }else{
        //发送创建用户请求
        var data = {};
        data.realname = realname;
        data.departmentId = department_id;
        data.positionId = position_id;

        $.ajax({
            type: "POST",
            url: contextPath + "/user/userEdit/" + id,
            data: data,
            dataType: "json",
            success: function(result){
                if (result.code == 000){
                    //成功
                    showAlert('修改成功');
                }else {
                    //失败
                    showAlert(result.message);
                }

            }
        });
    }

}