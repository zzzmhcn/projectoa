function getNoticeDtl() {
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);
    $.ajax({
        type: "POST",
        url: contextPath + "/notice/getNoticeDtl",
        data: {id:id},
        dataType: "json",
        success: function (result) {
            if(result.code == 000){
                $('#notice_dtl_title').text(result.value.title);
                $('#notice_dtl_main').text(result.value.notice);
                //发件人姓名再查一次 和时间一起注入span
                var realName = getSendUserName(result.value.sendId);
                $('#sendUserName').text(realName);
                $('#sendTime').text(result.value.createTime);
                //最后他打开了这条 就把这条从未读里删除
                setIsRead(result.value.id);
            }else{
                showAlert("获取站内信异常!")
            }
        }
    });
}

/**
 * 设为已读
 */
function setIsRead(noticeID) {
    $.ajax({
        type: "POST",
        url: contextPath + "/notice/setIsRead",
        data: {id:noticeID},
        dataType: "json",
        success: function (result) {
            //暂不需要成功后执行
        }
    });
}

/**
 * 取发件人真实姓名
 * 此方法与message公用
 */
function getSendUserName(sendID) {
    var realName = "";
    $.ajax({
        type: "POST",
        async:false,//同步
        url: contextPath + "/message/getSendUserName",
        data: {id:sendID},
        dataType: "json",
        success: function (result) {
            if(result.code == 000){
                realName = result.value;
            }
        }
    });
    return realName;
}