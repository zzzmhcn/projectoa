var vm_notice = new Vue({
    el: '#notice',
    data: {
        notices: [],
    },
    methods: {
        getNotices: getNotices
    },
    created: getInit()
})

function getInit() {
    getNotices();
}


/**
 * 获取所有未读信息(本人的)
 */
function getNotices() {
    $.ajax({
        type: "POST",
        url: contextPath + "/notice/getNotices",
        dataType: "json",
        success: function (result) {
            if (result.code == 000) {
                vm_notice.notices = result.value;
            } else {
                showAlert("获取站内信异常!")
            }
        }
    });
}