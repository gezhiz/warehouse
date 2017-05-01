<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
<#assign menu='itemExitOrder'/>
    <#include "../../common/head-meta.ftl">
</head>
<body class="site-navbar-small dashboard">
<#include "../../common/header.ftl">
<!-- Page -->
<div class="page animsition">
    <div class="page-content pediting-30 container-fluid">
        <div class="panel">

            <div class="panel-body container-fluid">

                <div class="page-header">
                    <h1 class="page-title">商品出库单列表</h1>
                    <div class="page-header-actions">
                        <div class="btn-group btn-group-sm" id="withBtnGroup" aria-label="Page Header Actions" role="group">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <button class="btn btn-primary pull-right" data-target="#editItemExitOrderModal" data-toggle="modal" id="editItemExitOrder" type="button">新增出库单</button>
                        <table class="table table-bordered text-center dataTable table-striped width-full vetical-middle"
                               id="itemExitOrderListTable">
                            <thead>
                            <tr>
                                <th class="text-center">id</th>
                                <th class="text-center">标题</th>
                                <th class="text-center">客户名称</th>
                                <th class="text-center">客户地址</th>
                                <th class="text-center">客户联系方式</th>
                                <th class="text-center">商品总数</th>
                                <th class="text-center">状态</th>
                                <th class="text-center">创建时间</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="editItemExitOrderModal" aria-hidden="false"
     role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <form class="modal-content" id="editItemExitOrderForm" yoyo_validate="true">
            <input type="hidden" name="id" value=""/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑出库单</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12 form-group">
                        <label >
                            出库单名称:
                            <p class="pull-right error-tip" yoyo_notNull="请输入出库单名称"></p>
                        </label>
                        <input type="text" class="form-control" name="title" placeholder="例如：牛仔城1909出库单" yoyo_notNull>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            客户名称:
                            <p class="pull-right error-tip" yoyo_notNull="请输入客户名称"></p>
                        </label>
                        <input type="text" class="form-control" name="clientName" placeholder="例如：李先生" yoyo_notNull>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            客户地址:
                            <p class="pull-right error-tip" yoyo_notNull="请输入客户地址"></p>
                        </label>
                        <input type="text" class="form-control" name="clientAddress" placeholder="例如：广州市增城区新塘镇牛仔城A座1909">
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            客户联系方式:
                            <p class="pull-right error-tip" yoyo_notNull="请输入客户联系方式"></p>
                        </label>
                        <input type="text" class="form-control" name="clientContact" placeholder="请输入客户联系方式">
                    </div>
                    <div class="col-sm-12">
                        <button class="btn btn-primary btn-outline pull-right" type="submit">提交</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- End Modal -->
<!-- End Page -->
<#include "../../common/scripts.ftl">
<#include "../../common/footer.ftl">
<script src="${request.contextPath}/statics/warehouse/js/itemExitOrderList.js"></script>
</body>
</html>