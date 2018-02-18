var vm = new Vue({
    el : '#logs',
    data : {
        fileNames : []
    },
    methods : {
        getFileNames : getFileNames,
        readFiles : readFiles
    },
    created : getInit()
})

function getInit() {
    getFileNames();
}

//查看所有log文件的文件名
function getFileNames() {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/getFileNames",
        success: function(result){
            vm.fileNames = result.value;
        }
    });
}

//读取某个log文件的具体内容 直接显示到内容区域
function readFiles() {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/readFiles",
        data: {fileName:$("#fileName").val()},
        dataType: "text",
        success: function(result){

        }
    });
}