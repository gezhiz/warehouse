$(function(){
    var $loginForm = $('#loginForm');
    $loginForm.submit(function (e) {
        e.preventDefault();
        var submitData = $loginForm.serializeObject();
        submitData.passwd = hex_md5(submitData.passwd);
        $.postJSON(
            baseUrl + '/sysops/do_login',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
                    $.jGrowl('登录成功', {
                        position: 'top-center',
                        theme: 'bg-success',
                        header: '提示'
                    });
                    location.href = baseUrl + "/sysops/index";
                } else {
                    $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
                    $.jGrowl(retJson.errmsg, {
                        position: 'top-center',
                        theme: 'bg-danger',
                        header: '提示'
                    });
                }
            }
        )
    });

})