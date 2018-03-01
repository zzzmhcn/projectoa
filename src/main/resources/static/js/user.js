var vm = new Vue({
    el : '#user',
    data : {
        departments : [],
        positions : [],
        userList : [],
    },
    methods : {
        getPosition : getPosition,
        getDepartment : getDepartment,
        getUser : getUser,
        deleteUser : deleteUser,
        reverseUser : reverseUser,
        setBest:setBest
    },
    created : getInit()

})

function getInit() {
    getDepartment();
    getPosition();
    getUser();
}

function setBest() {
    var id=event.target.id.replace("best_", "");
    $.ajax({
        type: "POST",
        url: contextPath + "/user/setBest",
        data: {id:id},
        dataType: "json",
        success: function(result){
            if (result.code == 000){
                //成功
                showAlert('设置成功');
                setTimeout(function(){
                    window.location.href="user"
                },1000);

            }else {
                //失败
                showAlert(result.message);
            }
        }
    });
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

//查询员工
function getUser() {

    var departmentId = $('#department').val() == 0 ? null : $('#department').val();
    var positionId = $('#position').val() == 0 ? null : $('#position').val();
    var realname = $('#realname').val() == '' ? null : $('#realname').val();
    var data = {};
    data.departmentId = departmentId;
    data.positionId = positionId;
    data.realname = realname;
    $.ajax({
        type : "POST",
        url : "/projectoa/user/userList",
        data : data,
        dataType : "json",
        success : function (result) {
            vm.userList = result.value.list;
        }
    })
}

function deleteUser() {
    var id=event.target.id.replace("delete_", "");
    showConfirm('确定删除此员工么？','删除员工','取消','删除').modal({
        onConfirm: function() {
            $.ajax({
                type: "POST",
                url: contextPath + "/user/userDelete/" + id,
                success: function(result){
                    if (result.code == 000){
                        //成功
                        showAlert('删除成功');
                        setTimeout(function(){
                            window.location.href="user"
                        },2000);

                    }else {
                        //失败
                        showAlert(result.message);
                    }

                }
            });
        },
        onCancel: function() {
            //干点啥
        }
    });
}

function reverseUser() {
    var id=event.target.id.replace("reverse_", "");
    showConfirm('确定重置此员工密码么？','重置密码','取消','重置').modal({
        onConfirm: function() {
            $.ajax({
                type: "POST",
                url: contextPath + "/user/userReverse/" + id,
                success: function(result){
                    if (result.code == 000){
                        //成功
                        showAlert('重置成功');
                        setTimeout(function(){
                            window.location.href="user"
                        },2000);

                    }else {
                        //失败
                        showAlert(result.message);
                    }

                }
            });
        },
        onCancel: function() {
            //干点啥
        }
    });
}

