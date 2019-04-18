function isIe() {
    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)) {
        alert('你是使用IE')
    } else if (navigator.userAgent.indexOf('Firefox') >= 0) {
        alert('你是使用Firefox')
    } else if (navigator.userAgent.indexOf('Opera') >= 0) {
        alert('你是使用Opera')
    } else {
        alert('你是使用其他的浏览器浏览网页！')
    }
}