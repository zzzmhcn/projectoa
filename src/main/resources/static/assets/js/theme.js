var saveSelectColor = {
    'Name': 'SelcetColor',
    'Color': 'theme-white'
}

function getThemeToggle() {
// 判断用户是否已有自己选择的模板风格
    if (storageLoad('SelcetColor')) {
        saveSelectColor = storageLoad('SelcetColor');
        $('body').attr('class', saveSelectColor.Color)
        if (saveSelectColor.Color == 'theme-white') {
            $('#eyesI').attr('class', 'am-icon-toggle-off');
        } else {
            $('#eyesI').attr('class', 'am-icon-toggle-on');
        }
    } else {
        storageSave(saveSelectColor);
        $('body').attr('class', 'theme-white')
    }
}

// session缓存
function storageSave(objectData) {
    sessionStorage.setItem(objectData.Name, JSON.stringify(objectData));
}

function storageLoad(objectName) {
    if (sessionStorage.getItem(objectName)) {
        return JSON.parse(sessionStorage.getItem(objectName))
    } else {
        return false
    }
}