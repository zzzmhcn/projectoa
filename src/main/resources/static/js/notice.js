var vm_notice = new Vue({
    el: '#notice',
    data: {
        notices: [],
        pageInfo : {},
        pageNum : 0
    },
    methods: {
        getNotices: getNotices
    },
    created: getInit()
})

function getInit() {
    getNotices(1);
}


/**
 * 获取所有未读信息(本人的)
 */
function getNotices(pageNum) {
    if (pageNum == 0){
        pageNum = 1;
    }
    $.ajax({
        type: "POST",
        url: contextPath + "/notice/getNotices",
        data : {
            "pageNum":pageNum
        },
        dataType: "json",
        success: function (result) {
            if (result.code == 000) {
                vm_notice.notices = result.value.list;
                vm_notice.pageInfo.pageNum = result.value.pageNum;
                vm_notice.pageInfo.pages = result.value.pages;
                vm_notice.pageInfo.prePage = result.value.prePage;
                vm_notice.pageInfo.nextPage = result.value.nextPage;
                vm_notice.pageInfo.isFirstPage = result.value.isFirstPage;
                vm_notice.pageInfo.isLastPage = result.value.isLastPage;
                vm_notice.pageNum = result.value.pageNum;
            } else {
                showAlert("获取站内信异常!")
            }
        }
    });
}