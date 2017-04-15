<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
<#assign menu='itemCategory'/>
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
                    <h1 class="page-title">商品类别配置列表</h1>
                    <div class="page-header-actions">
                        <div class="btn-group btn-group-sm" id="withBtnGroup" aria-label="Page Header Actions" role="group">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <button class="btn btn-primary pull-right" data-target="#editItemCategoryModal" data-toggle="modal" id="editItemCategory" type="button">新增类别</button>
                        <table class="table table-bordered text-center dataTable table-striped width-full vetical-middle"
                               id="itemCategoryListTable">
                            <thead>
                            <tr>
                                <th class="text-center">id</th>
                                <th class="text-center">类别</th>
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
<div class="modal fade" id="editItemCategoryModal" aria-hidden="false"
     role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <form class="modal-content" id="editItemCategoryForm" yoyo_validate="true">
            <input type="hidden" name="id" value=""/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑类别</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12 form-group">
                        <label >
                            类别名称:
                            <p class="pull-right error-tip" yoyo_notNull="请输入类别名称"></p>
                        </label>
                        <input type="text" class="form-control" name="name" placeholder="例如：牛仔裤" yoyo_notNull>
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
<script src="${request.contextPath}/statics/warehouse/js/itemCategoryList.js"></script>
</body>
</html>