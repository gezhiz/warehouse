/**
 * Created by doublez on 16/1/14.
 */
var YoYoValidate = {
    serializeObject: function($form){
        var a,o,h,i,e;
        a=$form.serializeArray();
        o={};
        h=o.hasOwnProperty;

        var h1 = function(p1, p2, p3){
            var i = p2.indexOf(".");
            if (i >= 0) {
                var i1 = p2.substring(0, i).trim();
                if (i1.length > 0) {
                    if (!h.call(p1, i1)) {
                        p1[i1] = {};
                    }

                    if (i + 1 < p2.length) {
                        h1(p1[i1], p2.substring(i + 1), p3);
                    }
                }
            } else {
                if (!h.call(p1, p2)) {
                    if (p2.substring(p2.length - 2) == '[]') {
                        p2 = p2.substring(0, p2.length - 2).trim();
                        if (!h.call(p1, p2)) {
                            p1[p2] = [];
                        }
                        p1[p2].push(p3);
                    } else {
                        p1[p2] = p3;
                    }
                } else {
                    if (!(p1[p2] instanceof Array)) {
                        var aa = [];
                        aa.push(p1[p2]);
                        p1[p2] = aa;
                    }

                    p1[p2].push(p3);
                }
            }
        };

        for(i=0;i<a.length;i++){
            e=a[i];
            e.name = e.name.trim();
            e.value = e.value.trim();
            h1(o, e.name, e.value);
        }

        var h2 = function (o, n, v) {
            var i = n.indexOf("[");
            if (i >= 0) {
                delete o[n];
                var i1 = n.substring(0, i).trim();
                if (!h.call(o, i1)) {
                    o[i1] = [];
                }
                o[i1].push(v);
            }
        }

        var h3 = function(o){
            if (o instanceof Array) {
                $.each(o, function (i) {
                    h3(o[i]);
                });
            } else if (o instanceof Object) {
                $.each(o, function (n, v) {
                    if (v instanceof Array) {
                        h3(v[i]);
                    } else if (v instanceof Object) {
                        h3(v);
                    }
                    h2(o, n, v);

                });
            }
        };

        h3(o);

        return o;
    },
    ensureJsonArray: function (json, key) {
        if (typeof json[key] == 'string') {
            var aa = [];
            aa.push(json[key]);
            json[key] = aa;
        }
    },
    findTip: function ($input) {
        var tip = $input.attr('yoyo_tip');
        if (_.isUndefined(tip) || _.isEmpty(tip)) {
            return $input.parent('div').find('p.error-tip')
        }

        return $('['+tip+']');

    },
    showErr : function($input, type) {
        if (_.isUndefined(type) || _.isNull(type)) {
            type = '';
        }
        type = type.trim();

        var $tip = YoYoValidate.findTip($input);
        YoYoValidate.showErrWithMsgInternel($input, $tip, $tip.attr(type));
    },

    showErrWithMsg : function($input, msg) {
        var $tip = YoYoValidate.findTip($input);;
        YoYoValidate.showErrWithMsgInternel($input, $tip, msg);
    },

    showErrWithMsgInternel : function($input, $tip, msg) {
        if ($input.is('select')) {
            // 针对select 做特殊处理
            $input.next().find('.select2-selection').css('border-color', '#ff5d3a');
            $input.parent().find('.select2-choice').css('border-color', '#ff5d3a');
        } else {
            $input.css('border-color', '#ff5d3a');
        }
        $tip.html(msg);
    },

    showCommonErrWithMsg : function($form, msg) {
        var $tip = $form.find('.common-error-tip');
        $tip.html(msg);
    },

    clearErr : function($input) {
        if ($input.is('select')) {
            // 针对select 做特殊处理
            var styleObj = $input.next().find('.select2-selection').prop('style');
            if(styleObj != undefined) {
                styleObj.removeProperty('border-color');
            }
            var styleObj2 = $input.parent().find('.select2-choice').prop('style');
            if(styleObj2 != undefined) {
                styleObj2.removeProperty('border-color');
            }
        } else {
            var styleObj = $input.prop('style');
            if(styleObj != undefined) {
                styleObj.removeProperty('border-color');
            }
        }
        YoYoValidate.findTip($input).html('');
    },
    prepareValidate : null,
    clearErrs: null,
    doValidate : null
};
$(function(){
    var $forms = $('form[yoyo_validate]');
    if (_.isEmpty($forms)) {
        return;
    }

    var showErr = YoYoValidate.showErr;

    var clearErr = YoYoValidate.clearErr;

    var validateStringLength = function($input) {
        var min = Number($input.attr('yoyo_minLength'));
        if (_.isNaN(min) || min < 0) {
            min = 0;
        }
        var max = Number($input.attr('yoyo_maxLength'));
        if (_.isNaN(max) || max < 0) {
            max = 0;
        }

        if (min == 0 && max == 0) {
            return true;
        }

        var val = $input.val();
        if (!_.isString(val)) {
            return true;
        }

        val = val.trim();
        if (min > 0 && val.length < min) {
            showErr($input, 'yoyo_minLength');
            return false;
        }

        if (max > 0 && val.length > max) {
            showErr($input, 'yoyo_maxLength');
            return false;
        }
        return true;
    };

    var validateNotNull = function($input) {
        if (_.isUndefined($input.attr('yoyo_notNull'))) {
            return true;
        }
        var val = $input.val();
        if (_.isString(val)) {
            val = val.trim();
        }
        if (_.isEmpty(val)) {
            showErr($input, 'yoyo_notNull');
            return false;
        }

        return true;
    };

    var validateEqual = function($input) {
        if (_.isUndefined($input.attr('yoyo_equal_to'))) {
            return true;
        }
        var val = $input.val();
        var equalTo = $input.attr('yoyo_equal_to');
        var equalsToVal = $(equalTo+"").val();
        if (equalsToVal == undefined) {
            alert('equalTo对应的选择器标签不存在');
            return;
        }
        if (_.isString(val)) {
            val = val.trim();
        }
        if (_.isString(equalsToVal)) {
            equalsToVal = equalsToVal.trim();
        }
        if (equalsToVal != val) {
            showErr($input, 'yoyo_equal_to');
            return false;
        }

        return true;
    };

    var validateNoEqual = function($input) {
        if (_.isUndefined($input.attr('yoyo_no_equal_to'))) {
            return true;
        }
        var val = $input.val();
        var noEqualTo = $input.attr('yoyo_no_equal_to');
        var hasEqual = false;
        var $noEqual = $(noEqualTo+"");
        var equalValue = '';
        for(var noEqualIndex = 0; noEqualIndex < $noEqual.length; noEqualIndex++) {
            for(var noIndex = 0; noIndex < $noEqual.length; noIndex++) {
                if(noEqualIndex != noIndex
                    && !_.isEmpty($($noEqual[noEqualIndex]).val())
                    && !_.isEmpty($($noEqual[noIndex]).val())
                    && $($noEqual[noEqualIndex]).val() == $($noEqual[noIndex]).val()
                ) {
                    hasEqual = true;
                    equalValue = $($noEqual[noEqualIndex]).val();
                    $($noEqual[noEqualIndex]).attr('hasNoEqualError');
                }
            }
        }
        for(var noEqualIndex = 0; noEqualIndex < $noEqual.length; noEqualIndex++) {
            var hasNoEqualError = $($noEqual[noEqualIndex]).attr('hasNoEqualError');
            if (hasNoEqualError != undefined) {
                clearErr($($noEqual[noEqualIndex]));
            } else {
                $($noEqual[noEqualIndex]).removeAttr('hasNoEqualError');
            }
        }

        if (hasEqual == true && equalValue == val) {
            showErr($input, 'yoyo_no_equal_to');
            return false;
        }
        return true;
    };

    var validateRegexInternal = function($input, regex) {
        var val = $input.val();
        if (!_.isString(val)) {
            return true;
        }
        var regexObj = new RegExp(regex);
        return regexObj.test(val.trim())
    };

    var validateEmail = function($input) {
        if (_.isUndefined($input.attr('yoyo_email'))) {
            return true;
        }
        if (!validateRegexInternal($input, '^([a-zA-Z0-9]+[-|_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$')) {
            showErr($input, 'yoyo_email');
            return false;
        }
        return true;
    };
    var validateUrl = function($input){
        if (_.isUndefined($input.attr('yoyo_url')) || _.isEmpty($input.val())) {
            return true;
        }
        var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
            + '(([0-9a-zA-Z_!~*\'().&=+$%-]+: )?[0-9a-zA-Z_!~*\'().&=+$%-]+@)?' //ftp的user@
            + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
            + '|' // 允许IP和DOMAIN（域名）
            + '([0-9a-zA-Z_!~*\'()-]+.)*' // 域名- www.
            + '([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z].' // 二级域名
            + '[a-zA-Z]{2,6})' // first level domain- .com or .museum
            + '(:[0-9]{1,4})?' // 端口- :80
            + '((/?)|' // a slash isn't required if there is no file name
            + '(/[0-9a-zA-Z_!~*\'().;?:@&=+$,%#-]+)+/?)$';
        if (!validateRegexInternal($input,strRegex)) {
            showErr($input, 'yoyo_url');
            return false;
        }
        return true;
    }

    var validateMobilePhone = function($input) {
        if (_.isUndefined($input.attr('yoyo_mobilePhone'))) {
            return true;
        }
        if (!validateRegexInternal($input, '^[1][3,4,5,7,8][0-9]{9}$')) {
            showErr($input, 'yoyo_mobilePhone');
            return false;
        }
        return true;
    };

    var validateRegex = function($input) {
        if (_.isUndefined($input.attr('yoyo_regex'))) {
            return true;
        }
        if (!validateRegexInternal($input, $input.attr('yoyo_regex'))) {
            showErr($input, 'yoyo_email');
            return false;
        }
        return true;
    };

    var doValidate = function($input){
        if (_.isUndefined($input)) {
            return true;
        }
        if (!validateStringLength($input)
            || !validateNotNull($input)
            || !validateEqual($input)
            || !validateNoEqual($input)
            || !validateEmail($input)
            || !validateRegex($input)
            || !validateMobilePhone($input)
            || !validateUrl($input)) {
            return false;
        }

        clearErr($input);
        return true;
    };

    var pushAll = function(arr, items) {
        if (!_.isEmpty(items)) {
            $.each(items, function (i, item) {
                arr.push(item);
            });
        }
    }

    var handleChange = function($inputs) {
        if (!_.isEmpty($inputs)) {
            $.each($inputs, function(j, input){
                $(input).change(function(){
                    doValidate($(this));
                });
            });
        }
    }

    var handleSubmit = function($inputs) {
        var success = true;
        if (!_.isEmpty($inputs)) {
            $.each($inputs, function(j, input){
                if (!doValidate($(input))) {
                    success = false;
                }
            });
        }
        return success;
    }

    YoYoValidate.clearErrs = function($container) {
        var $inputs = $container.find('input[name]');
        var $select = $container.find('select[name]');
        var $textarea = $container.find('textarea[name]');
        $.each($inputs,function(i,inputItem){
            clearErr($(inputItem));
        });
        $.each($select,function(i,selectItem){
            clearErr($(selectItem));
        });
        $.each($textarea,function(i,textareaItem){
            clearErr($(textareaItem));
        });
    }

    YoYoValidate.prepareValidate = function($container){
        var $inputs = $container.find('input[name]');
        var $select = $container.find('select[name]');
        var $textarea = $container.find('textarea[name]');
        handleChange($inputs);
        handleChange($select);
        handleChange($textarea);
    };

    YoYoValidate.doValidate = function($container) {
        YoYoValidate.showCommonErrWithMsg($container, '');
        var $inputs = $container.find('input[name]');
        var $select = $container.find('select[name]');
        var $textarea = $container.find('textarea[name]');

        var success = true;
        success = handleSubmit($inputs) && success;
        success = handleSubmit($select) && success;
        success = handleSubmit($textarea) && success;
        return success;
    };

    $.each($forms, function(i, form){
        YoYoValidate.prepareValidate($(form));

        $(form).find('[type="submit"]').click(function(){

            if (YoYoValidate.doValidate($(form))) {
                $(form).submit();
            }
            return false;
        });

    });

});
