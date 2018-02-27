var vm_log = new Vue({
    el: '#log',
    data: {
        fileNames: []
    },
    methods: {
        getFileNames: getFileNames,
        readFiles: readFiles
    },
    created: getInit()
})

function getInit() {
    getFileNames();
}

//查看所有log文件的文件名
function getFileNames() {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/getFileNames",
        success: function (result) {
            vm_log.fileNames = result.value.reverse();
        }
    });
}

//读取某个log文件的具体内容 直接显示到内容区域
function readFiles() {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/readFiles",
        data: {fileName: $("#fileName").val()},
        success: function (result) {
            if (result.code == 000) {
                $('#logPre').html(result.value);
            } else {
                $('#logPre').html(result.message);
            }
            //这是一个代码高亮的js但是他比较2 因为他必须在代码出现之后加载才有效，所以最后加载他
            $.getScript(contextPath + "/prism/prism.js");
        }
    });
}

/**
 * 自适应高度
 */
function reSizePre() {
    //方法就用最简单粗暴的了，屏幕分辨率高度除以倍数取整数加单位
    var maxHeight = parseInt(window.screen.availHeight / 18) + 'rem';
    $('.am-pre-scrollable').css('max-height', maxHeight);
}