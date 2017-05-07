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
                    <h1 class="page-title">商品【${item.name}】单品管理</h1>
                    <div class="page-header-actions">
                        <div class="btn-group btn-group-sm" id="withBtnGroup" aria-label="Page Header Actions" role="group">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <button class="btn btn-primary pull-right" data-target="#editItemSkuModal" data-toggle="modal" id="editItemSku" type="button">新增单品</button>
                        <table class="table table-bordered text-center dataTable table-striped width-full vetical-middle"
                               id="itemSkuListTable">
                            <thead>
                            <tr>
                                <th class="text-center">id</th>
                                <th class="text-center">商品颜色</th>
                                <th class="text-center">商品尺码</th>
                                <th class="text-center">库存数</th>
                                <th class="text-center">历史总库存</th>
                                <th class="text-center">价格(￥)</th>
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
<div class="modal fade" id="editItemSkuModal" aria-hidden="false"
     role="dialog">
    <div class="modal-dialog">
        <form class="modal-content" id="editItemSkuForm" yoyo_validate="true">
            <input type="hidden" name="itemId" value="${item.id}"/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">添加【${item.name}】的单品</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12 form-group">
                        <label >
                            商品颜色:
                            <p class="pull-right error-tip" yoyo_notNull="请选择商品颜色"></p>
                        </label>
                        <select type="text" class="form-control select"  data-plugin="select2" name="color" placeholder="例如：夏季牛仔裤" yoyo_notNull>
                        <#list itemColorPageBean.list as itemColor>
                            <option value="${itemColor.name}">${itemColor.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            商品尺寸:
                            <p class="pull-right error-tip" yoyo_notNull="请选择尺寸"></p>
                        </label>
                        <select type="text" class="form-control select"  data-plugin="select2" name="itemSize" placeholder="例如：夏季牛仔裤" yoyo_notNull>
                        <#list itemSizePageBean.list as itemSize>
                            <option value="${itemSize.name}">${itemSize.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            商品价格:
                            <p class="pull-right error-tip" yoyo_notNull="请输入价格"></p>
                        </label>
                        <input type="number" class="form-control" name="doublePrice" placeholder="例如：10.20" value="0" yoyo_notNull>
                    </div>
                    <div class="col-lg-12 form-group">
                        <label >
                            初始库存:
                            <p class="pull-right error-tip" yoyo_notNull="请输入初始库存"></p>
                        </label>
                        <input type="number" class="form-control" name="initStock" placeholder="例如：10" value="0" yoyo_notNull>
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

<!-- Modal -->
<div class="modal fade" id="inStockModal" aria-hidden="false"
     role="dialog">
    <div class="modal-dialog">
        <form class="modal-content" id="inStockForm" yoyo_validate="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">【${item.name}】【<span id="inStockColor"></span>】【<span id="inStockSize"></span>】入库</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <input type="hidden" name="skuId" value=""/>
                    <div class="col-sm-12 form-group">
                        <label >
                            入库数量:
                            <p class="pull-right error-tip" yoyo_notNull="请输入入库数量"></p>
                        </label>
                        <input type="number" class="form-control" name="stockCount" placeholder="例如：10" value="" yoyo_notNull>
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

<!-- Modal -->
<div class="modal fade" id="exitStockModal" aria-hidden="false"
     role="dialog">
    <div class="modal-dialog">
        <form class="modal-content" id="exitStockForm" yoyo_validate="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">【${item.name}】【<span id="inStockColor"></span>】【<span id="inStockSize"></span>】加入出库单</h4>
            </div>
            <div class="modal-body">
                <#if itemExitOrderPageBean.list?size == 0>
                <div class="row text-center">
                    <a href="${request.contextPath}/sysops/itemExitOrder/itemExitOrderList" class="error-tip">去创建出库单</a>
                </div>
                <#else>
                    <div class="row">
                        <input type="hidden" name="skuId" value=""/>
                        <div class="col-sm-12 form-group">
                            <label >
                                出库数量:
                                <p class="pull-right error-tip" yoyo_notNull="请输入出库数量"></p>
                            </label>
                            <input type="number" class="form-control" name="skuCount" placeholder="例如：10" value="" yoyo_notNull>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 form-group">
                            <label >
                                出库单:
                                <p class="pull-right error-tip" yoyo_notNull="请选择出库单"></p>
                            </label>
                            <select type="text" class="form-control select"  data-plugin="select2" name="itemExitOrderId" yoyo_notNull>
                                <#list itemExitOrderPageBean.list as itemExitOrder>
                                    <option value="${itemExitOrder.id}">${itemExitOrder.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <button class="btn btn-primary btn-outline pull-right" type="submit">提交</button>
                        </div>
                    </div>
                </#if>

            </div>
        </form>
    </div>
</div>
<!-- End Modal -->
<!-- End Page -->
<#include "../../common/scripts.ftl">
<#include "../../common/footer.ftl">
<script src="${request.contextPath}/statics/warehouse/js/itemSkuList.js"></script>
<script>
    var itemId = ${item.id};
</script>
</body>
</html>