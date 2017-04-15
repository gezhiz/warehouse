$(function(){
    var $loginForm = $('#loginForm');
    $loginForm.submit(function (e) {
        e.preventDefault();
        var submitData = $loginForm.serializeObject();
        submitData.password = hex_md5(submitData.password);
        $.postJSON(
            baseUrl + '/sysops/do_login',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    toastr.success('登录成功','');
                    location.href = baseUrl + "/sysops/index";
                } else {
                    toastr.error(retJson.errmsg, '');
                }
            }
        )
    });

})