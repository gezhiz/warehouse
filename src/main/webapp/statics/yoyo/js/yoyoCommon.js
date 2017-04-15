/**
 * Created by doublez on 16/7/6.
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+" : this.getHours(), //小时
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}
var YoYoDateTimeFormat = "yyyy-MM-dd hh:mm:ss";
var YoYoDateFormat = "yyyy-MM-dd";
var urlRegexStr = '^((https|http|ftp|rtsp|mms)?://)'
    + '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@
    + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
    + '|' // 允许IP和DOMAIN（域名）
    + '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
    + '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
    + '[a-z]{2,6})' // first level domain- .com or .museum
    + '(:[0-9]{1,4})?' // 端口- :80
    + '((/?)|' // a slash isn't required if there is no file name
    + '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$';
var urlRegex = new RegExp(urlRegexStr);
var previewImg = function (self) {
    var src = $(self).attr('src');
    if (!_.isEmpty(src)) {
        window.open(src, '_blank');
    }
    return false;
}
var toLink = function (link) {
    if (!_.isEmpty(link)) {
        location.href = link;
    }
}

var removeDisabled = function ($tag) {
    $tag.removeAttr('disabled');
    $tag.removeClass('disabled');
}

var commonValidateError = function (error, element) {

    // Styled checkboxes, radios, bootstrap switch
    if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container')) {
        if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
            error.prependTo(element.parent().parent().parent().parent());
        }
        else {
            error.prependTo(element.parent().parent().parent().parent().parent());
        }
    }

    // Unstyled checkboxes, radios
    else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
        error.prependTo(element.parent().parent().parent());
    }

    // Inline checkboxes, radios
    else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
        error.prependTo(element.parent().parent());
    }

    // Input group, styled file input
    else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
        error.prependTo(element.parent().parent());
    }
    else {
        error.prependTo(element.parent());
    }
};

var backToLastPage = function () {
    history.go(-1);
}

var yoyoResetForm = function ($form,validator) {
    if($form == undefined) {
        alert("请传入表单的jQuery对象");
        return;
    }
    if(validator == undefined) {
        alert('验证类不能为空');
        return;
    }
    $form.find('input').val('');
    $form.find('textarea').val('');
    $form.find('textarea').html('');
    $form.find('.select').val(null).trigger("change");
    addSysopserFormValidator.resetForm();
}

var yoyoResetForm2 = function($form) {
    if($form == undefined) {
        alert("请传入表单的jQuery对象");
        return;
    }
    $form.find('input').val('');
    $form.find('textarea').val('');
    $form.find('textarea').html('');
    $form.find('select').val(null);
    $form.find('.select').val(null).trigger("change");
    YoYoValidate.clearErrs($form);
}


$(function () {
    $('.animsition').css('opacity', '2');
})
var switchDateFormat = function getMsgFormatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    var now = new Date();
    var nowYear = now.getFullYear();
    var nowMonth = now.getMonth() + 1;
    var nowDay = now.getDate();
    if (nowYear == year && nowMonth == month && nowDay == day) {
        if (minute < 10) {
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }
    if (nowYear == year && nowMonth == month) {
        var day1 = nowDay - day;
        return day1 + "天前";
    }
    if (nowYear == year) {
        var date3 = now.getTime() - date.getTime();
        var days=Math.floor(date3/(24*3600*1000));
        if(days>30){
            return nowMonth - month + "个月前"
        }else if(days<1) {
            days = 1 ;
        }
        return days + "天前";
    }
    return year + "-" + month + "-" + day;
};