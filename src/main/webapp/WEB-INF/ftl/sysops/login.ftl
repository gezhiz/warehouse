<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
<#include "../common/head-meta.ftl">
</head>
<body>
<!-- Page -->
<div class="page animsition" data-animsition-in="fade-in" data-animsition-out="fade-out">
    <div class="page-content">
        <div class="row">
            <div class="col-xs-4 col-xs-offset-4">
                <div class="panel">
                    <div class="panel-body">
                        <div class="brand  text-center">
                            <img class="brand-img"
                                 src="${request.contextPath}/statics/warehouse/assets/images/logo-blue.png" alt="...">
                            <h2 class="brand-text font-size-18">仓储管理系统</h2>
                        </div>
                        <form id="loginForm" action="#" yoyo_validate="true">
                            <div class="form-group">
                                <label>用户名<p class="pull-right error-tip" yoyo_notNull="请输入用户名"></p></label>
                                <input type="text" class="form-control" yoyo_notNull name="username"/>
                            </div>
                            <div class="form-group">
                                <label>密码<p class="pull-right error-tip" yoyo_notNull="请输入用户名"></p></label>
                                <input type="password" class="form-control" yoyo_notNull name="password"/>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block btn-lg margin-top-40">登录</button>
                        </form>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- End Page -->
</body>

<#include "../common/scripts.ftl" >
<script src="${request.contextPath}/statics/warehouse/js/login.js"></script>
</html>