/**
 * Created by gezz on 2017/3/18.
 */
var $editItemExitOrderModal = $('#editItemExitOrderModal');
var $editItemExitOrderForm = $('#editItemExitOrderForm');
var $shippedForm = $('#shippedForm');
var $shippedModal = $('#shippedModal');
$(function(){
    $editItemExitOrderForm.submit(function (e) {
        e.preventDefault();
        var submitData = $editItemExitOrderForm.serializeObject();
        $.postJSON(
            baseUrl + '/sysops/itemExitOrder/edit_item_exit_order',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    yoyoResetForm2($editItemExitOrderForm);
                    toastr.success('提交成功', '', {positionClass: 'toast-top-center'});
                    $editItemExitOrderModal.modal('hide');
                    $dataTable.fnFilter();
                } else {
                    toastr.error(retJson.errmsg, '', {});
                }
            }
        )
    });
    $shippedForm.submit(function (e) {
        e.preventDefault();
        var submitData = $shippedForm.serializeObject();
        $.postJSON(
            baseUrl + '/sysops/itemExitOrder/shipped',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    yoyoResetForm2($shippedForm);
                    toastr.success('出库发货成功', '', {positionClass: 'toast-top-center'});
                    $editItemExitOrderModal.modal('hide');
                    $dataTable.fnFilter();
                    $shippedModal.modal('hide');
                } else {
                    toastr.error(retJson.errmsg, '', {});
                }
            }
        )
    });

    var defaults = $.components.getDefaults("dataTable");
    var options = $.extend(true, {}, defaults, {
        "pageLength": 10,
        keys: true,
        //"bPaginate": true,
        "bSort": false,
        "language": {
            "sInfo": '第_PAGE_页,共_PAGES_页,共_TOTAL_条',
            sInfoFiltered: '',
            "sInfoEmpty": '',
            "emptyTable": '没有数据',
            "sZeroRecords": "没有数据",
        },
        //"oTableTools": {
        //    "sSwfPath": baseUrl+"/static/global/vendor/datatables-tabletools/swf/copy_csv_xls.swf"
        //},
        "dom": 't<"row"<"col-xs-12 col-md-6"i><"col-xs-12 col-md-6"p>>',
        columns: [
            {
                "data": function (source, type, val) {
                    return source.id;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.title;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.clientName == undefined ? '(无)' : source.clientName;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.clientAddress == undefined ? '(无)' : source.clientAddress;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.clientContact == undefined ? '(无)' : source.clientContact;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.itemCount == undefined ? '(无)' : source.itemCount;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.extMap.statusEnumShow == undefined ? '(无)' : source.extMap.statusEnumShow;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.createTime == undefined ? '(无)' : new Date(source.createTime).format("yyyy-MM-dd hh:mm:ss");
                },
            },
            {
                "data": function (source, type, val) {
                    var operatorContent = '<a editItemExitOrder="'+source.id+'" href="javascript:void(0);">编辑</a>';
                    if(source.status == 1) {
                        operatorContent += '&nbsp;&nbsp;<a href="javascript:void(0);" shippedTitle="'+source.title+'" shipped="'+source.id+'">发货</a>';
                    }
                    operatorContent += '&nbsp;&nbsp;<a href="'+baseUrl+'/sysops/itemExitOrder/itemExitOrderDetail/'+source.id+'">出库单</a>';
                    return operatorContent;
                },
            }
        ],
        serverSide: true,
        ajax: {
            url: baseUrl + '/sysops/itemExitOrder/item_exit_order_page_list',
            data: function (data) {
                //请求参数
                var filterData = $.extend({}, data, {});
                filterData['page'] = data['start'] / data['length'] + 1;
                filterData['pageExitOrder'] = data['length'];
                return filterData;
            },
            dataFilter: function (dataString) {
                //处理返回值,得到一个datatable能够解析的json串
                var json = {};
                var retJson = jQuery.parseJSON(dataString);
                if (retJson.errno != 0) {
                    json.recordsTotal = 0;
                    json.recordsFiltered = 0;
                    json.data = [];
                    alertify.alert(retJson.errmsg);
                } else {
                    var pageBean = retJson.data;
                    json.recordsTotal = pageBean.dataCount;//当前记录数
                    json.recordsFiltered = pageBean.totalDataCount;//总记录数
                    json.data = pageBean.list;//数据列表
                    json.page = pageBean.page;
                    json.pages = pageBean.totalPage;
                }
                $('#queryAllCount').html(json.recordsFiltered);
                return JSON.stringify(json); // return JSON string
            }
        },
        fnDrawCallback: function () {
            //表格绘制完成后
            $('[editItemExitOrder]').click(function() {
                var $this = $(this);
                var itemExitOrderId = $this.attr('editItemExitOrder');
                $.getJSON(
                    baseUrl + '/sysops/itemExitOrder/item_exit_order_info',
                    {
                        itemExitOrderId: itemExitOrderId
                    },
                    function (retJson) {
                        if (retJson.errno == 0) {
                            var itemExitOrderId = retJson.data;
                            $editItemExitOrderModal.modal('show');
                            $editItemExitOrderModal.find('[name=id]').val(itemExitOrderId.id);
                            $editItemExitOrderModal.find('[name=title]').val(itemExitOrderId.title);
                            $editItemExitOrderModal.find('[name=clientName]').val(itemExitOrderId.clientName);
                            $editItemExitOrderModal.find('[name=clientAddress]').val(itemExitOrderId.clientAddress);
                            $editItemExitOrderModal.find('[name=clientContact]').val(itemExitOrderId.clientContact);
                        } else {
                            toastr.error(retJson.errmsg, '');
                        }
                    }
                )

            });
            $('[shipped]').click(function() {
                var itemExitOrderId = $(this).attr('shipped');

                $.getJSON(
                    baseUrl + '/sysops/itemExitOrder/item_exit_order_detail',
                    {
                        itemExitOrderId: itemExitOrderId
                    },
                    function(retJson) {
                        if(retJson.errno == 0) {
                            var itemExitOrder = retJson.data;
                            // var itemExitOrderDetails = itemExitOrder.extMap.itemExitOrderDetail;
                            // $shippedModal.find('[shippedTitle]').html(itemExitOrder.title);
                            // $shippedModal.find('[shippedName]').html(itemExitOrder.clientName);
                            // $shippedModal.find('[shippedContact]').html(itemExitOrder.clientContact);
                            // $shippedModal.find('[shippedAddress]').html(itemExitOrder.clientAddress);
                            // for (var i = 0; i < itemExitOrderDetails.length; i++) {
                            //     var itemExitOrderDetail = itemExitOrderDetails[i];
                            //     var itemExitDetailTrContent = '<tr>';
                            //     itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemCategoryName+'</td>';
                            //     itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemName+'</td>';
                            //     itemExitDetailTrContent += '<td>'+itemExitOrderDetail.itemSize+'</td>';
                            //     itemExitDetailTrContent += '<td>'+itemExitOrderDetail.color+'</td>';
                            //     itemExitDetailTrContent += '<td>'+itemExitOrderDetail.skuCount+'</td>';
                            //     itemExitDetailTrContent += '</tr>';
                            // }
                            $shippedModal.find('#itemExitOrderDetailTableDiv').html(itemExitOrderDetailContent(itemExitOrder));
                        } else {
                            toastr.error(retJson.errmsg, '', {});
                        }
                    }
                )
                var shippedTitle = $(this).attr('shippedTitle');
                $shippedModal.find('[name=itemExitOrderId]').val(itemExitOrderId);
                $shippedModal.find('#shippedTitle').html(shippedTitle);
                $shippedModal.modal('show');
            });
        }
    });

    var $dataTable = $('#itemExitOrderListTable').dataTable(options);

    $('#editItemExitOrder').click(function() {
        yoyoResetForm2($editItemExitOrderForm);
    });
});