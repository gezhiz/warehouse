<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
<#assign menu='item'/>
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
                    <h1 class="page-title">商品列表</h1>
                    <div class="page-header-actions">
                        <div class="btn-group btn-group-sm" id="withBtnGroup" aria-label="Page Header Actions" role="group">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <button class="btn btn-primary pull-right" data-target="#editItemModal" data-toggle="modal" id="editItem" type="button">新增</button>
                        <table class="table table-bordered text-center dataTable table-striped width-full vetical-middle"
                               id="itemListTable">
                            <thead>
                            <tr>
                                <th class="text-center">id</th>
                                <th class="text-center">商品名称</th>
                                <th class="text-center">库存数</th>
                                <th class="text-center">历史总库存</th>
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
<div class="modal fade" id="editItemModal" aria-hidden="false"
     role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <form class="modal-content" id="editItemForm" yoyo_validate="true">
            <input type="hidden" name="id" value=""/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑商品</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12 form-group">
                        <label >
                            商品名称:
                            <p class="pull-right error-tip" yoyo_notNull="请输入名称"></p>
                        </label>
                        <input type="text" class="form-control" name="name" placeholder="例如：夏季牛仔裤" yoyo_notNull>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            商品类别:
                            <p class="pull-right error-tip" yoyo_notNull="请选择商品类别"></p>
                        </label>
                        <select type="text" class="form-control select"  data-plugin="select2" name="itemCategoryId" placeholder="例如：夏季牛仔裤" yoyo_notNull>
                        <#list itemCategoryPageBean.list as itemCategory>
                            <option value="${itemCategory.id}">${itemCategory.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            商品描述:
                            <p class="pull-right error-tip" yoyo_notNull="请输入商品描述"></p>
                        </label>
                        <textarea type="text" rows="5" class="form-control" name="itemDesc" placeholder="您可以在此记录商品的一些特征描述、备注等信息" yoyo_notNull></textarea>
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
<script src="${request.contextPath}/statics/warehouse/js/itemList.js"></script>
</body>
</html>