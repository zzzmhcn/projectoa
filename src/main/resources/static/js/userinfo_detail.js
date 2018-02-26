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
        getUserinfo : getUserinfo,
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
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);
    $.ajax({
        type: "POST",
        url: contextPath + "/user/getUser",
        data: {'userID':id},
        //dataType: "json",
        success: function(result){
            vm.user = result.value;
        }
    });

}

//用户信息回显
function getUserinfo() {
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);
    $.ajax({
        type : "POST",
        url : contextPath + "/user/userinfo/detail/" + id,
        success : function (result) {
            vm.userinfo = result.value;
            if (vm.userinfo.birthday != null){
                vm.userinfo.birthday = vm.userinfo.birthday.substring(0, 10);
            }
        }
    });

}




