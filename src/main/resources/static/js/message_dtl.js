function getMessageDtl() {
    var url = window.location.href;
    var index = url.lastIndexOf("\/");
    var id  = url.substring(index + 1, url.length);
    $.ajax({
        type: "POST",
        url: contextPath + "/message/getMessageDtl",
        data: {id:id},
        dataType: "json",
        success: function (result) {
            if(result.code == 000){
                $('#message_dtl_title').text(result.value.title);
                $('#message_dtl_main').text(result.value.message);
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
function setIsRead(messageID) {
    $.ajax({
        type: "POST",
        url: contextPath + "/message/setIsRead",
        data: {id:messageID},
        dataType: "json",
        success: function (result) {
            //后台成功后 前台做个假的-1效果 弥补刷新前页面数字还是老的问题
            if(result.code == 000){
                changenums('messageUnReadNum');
            }

        }
    });
}
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