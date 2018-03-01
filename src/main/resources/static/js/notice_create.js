/**
 * 发送公告 默认就是发送给所有人
 */
function sendNotice() {
    var title = $('#noticeTile').val();
    var notice = $('#noticeMain').val();
    if (title == null || title == "" || title == undefined) {
        showAlert("请输入标题");
    } else if (title.length > 20) {
        showAlert("标题不能超过20字");
    } else if (notice == null || notice == "" || notice == undefined) {
        showAlert("请输入内容");
    } else if (notice.length > 500) {
        showAlert("内容不能超过500字");
    } else {
        var data = {};
        data.title = title;
        data.notice = notice;
        $.ajax({
            type: "POST",
            url: contextPath + "/notice/send_notice",
            data: data,
            dataType: "json",
            success: function (result) {
                if (result.code == 000) {
                    showAlert("发送成功!");
                } else {
                    showAlert("发送失败 " + result.message);
                }
            }
        });
    }
}

