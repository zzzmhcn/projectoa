var vm = new Vue({
    el : '#user',
    data : {
        departments : [],
        positions : []
    },
    methods : {
        getPosition : getPosition,
        getDepartment : getDepartment,
    },
    created : getInit()

})

function getInit() {
    getDepartment();
    getPosition();
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

