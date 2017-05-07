<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
<#assign menu='itemExitOrder'/>
    <#include "../../common/head-meta.ftl">
</head>
<body class="site-navbar-small dashboard">
<!-- Page -->
<div class="page animsition">
    <div class="page-content pediting-30 container-fluid">
        <div class="panel">

            <div class="panel-body container-fluid">
                <div class="row" id="itemExitOrderDetailContent"></div>
                <br>
            </div>
        </div>
    </div>
</div>
<!-- End Page -->
<#include "../../common/scripts.ftl">
<#include "../../common/footer.ftl">
</body>
<script>
    var itemExitOrder = JSON.parse('${itemExitOrder?json_string}');
    $('#itemExitOrderDetailContent').html(itemExitOrderDetailContent(itemExitOrder));
</script>
</html>