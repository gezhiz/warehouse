/**
 * Created by gezz on 2017/5/7.
 */
var itemExitOrderDetailContent = function(itemExitOrder) {
    var resultContent = '<table class="table table-bordered dataTable table-striped width-full vetical-middle">'+
        '<thead>'+
        '<div class="row">'+
        '<h3 shippedTitle class="text-center">'+itemExitOrder.title+'</h3>'+
        '<div class="col-md-3">'+
        '姓名：<span shippedName>'+itemExitOrder.clientName+'</span>'+
        '</div>'+
        '<div class="col-md-4">'+
        '电话：<span shippedContact>'+itemExitOrder.clientContact+'</span>'+
        '</div>'+
        '<div class="col-md-5">'+
        '创建时间：<span shippedContact>'+new Date(itemExitOrder.createTime).format(YoYoDateTimeFormat)+'</span>'+
        '</div>'+
        '<div class="col-md-12">'+
        '地址：<span shippedAddress>'+itemExitOrder.clientAddress+'</span>'+
        '</div>'+
        '</div>'+
        '<tr class="text-center">'+
        '<th>商品类别</th>'+
        '<th>商品名称</th>'+
        '<th>商品尺码</th>'+
        '<th>商品颜色</th>'+
        '<th>商品数量</th>'+
        '</tr>'+
        '</thead>'+
        '<tbody shippedTBody>';
    var itemExitOrderDetails = itemExitOrder.extMap.itemExitOrderDetails;
    for (var i = 0; i < itemExitOrderDetails.length; i++) {
        var itemExitOrderDetail = itemExitOrderDetails[i];
        var itemExitDetailTrContent = '<tr>';
        itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemCategoryName+'</td>';
        itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemName+'</td>';
        itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemSize+'</td>';
        itemExitDetailTrContent += '<td>'+itemExitOrderDetail.color+'</td>';
        itemExitDetailTrContent += '<td>'+itemExitOrderDetail.skuCount+'</td>';
        itemExitDetailTrContent += '</tr>';
        resultContent += itemExitDetailTrContent;
    }
    resultContent += '</tbody>';
    '</table>';
    return resultContent;
}