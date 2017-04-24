/**
 * Created by gezz on 2017/3/18.
 */
$(function(){
    var $editItemSkuModal = $('#editItemSkuModal');
    var $editItemSkuForm = $('#editItemSkuForm');
    var $inStockModal = $('#inStockModal');
    var $inStockForm = $('#inStockForm');
    $editItemSkuForm.submit(function (e) {
        e.preventDefault();
        var submitData = $editItemSkuForm.serializeObject();
        $.postJSON(
            baseUrl + '/sysops/sku/edit_item_sku',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    // yoyoResetForm2($editItemSkuForm);
                    toastr.success('提交成功', '', {positionClass: 'toast-top-center'});
                    $editItemSkuModal.modal('hide');
                    $dataTable.fnFilter();
                } else {
                    toastr.error(retJson.errmsg, '', {});
                }
            }
        )
    });
    $inStockForm.submit(function (e) {
            e.preventDefault();
            var submitData = $inStockForm.serializeObject();
            $.postJSON(
                baseUrl + '/sysops/inStock/in_stock',
                submitData,
                function(retJson) {
                    if(retJson.errno == 0) {
                        yoyoResetForm2($inStockForm);
                        toastr.success('入库成功', '', {positionClass: 'toast-top-center'});
                        $inStockModal.modal('hide');
                        $dataTable.fnFilter();
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
                    return source.color;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.itemSize;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.stock;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.totalStock;
                },
            },
            {
                "data": function (source, type, val) {
                    return source.price / 100;
                },
            },
            {
                "data": function (source, type, val) {
                    return'<a  href="javascript:void(0);">出库</a>&nbsp;'+
                    '<a inStockItem data-target="#inStockModal" skuId="'+source.id+'" skuColor="'+source.color+'" skuSize="'+source.itemSize+'" data-toggle="modal">入库</a>';
                },
            },

        ],
        serverSide: true,
        ajax: {
            url: baseUrl + '/sysops/sku/item_sku_page_list',
            data: function (data) {
                //请求参数
                var filterData = $.extend({}, data, {});
                filterData['page'] = data['start'] / data['length'] + 1;
                filterData['pageItem'] = data['length'];
                filterData['itemId'] = itemId;
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
            $('[editItemId]').click(function() {
                var $this = $(this);
                var itemId = $this.attr('editItemId');
                $.getJSON(
                    baseUrl + '/sysops/item/item_info',
                    {
                        itemId: itemId
                    },
                    function (retJson) {
                        if (retJson.errno == 0) {
                            var item = retJson.data;
                            $editItemSkuModal.modal('show');
                            $editItemSkuModal.find('[name=id]').val(item.id);
                            $editItemSkuModal.find('[name=name]').val(item.name);
                            $editItemSkuModal.find('[name=itemDesc]').val(item.itemDesc);
                            $editItemSkuModal.find('[name=itemCategoryId]').val(item.itemCategoryId).change();
                        } else {
                            toastr.error(retJson.errmsg, '');
                        }
                    }
                )

            });
            $('#itemListTable_info').removeAttr('aria-live');

            $('[inStockItem]').click(function() {
                var $this = $(this);
                var skuId = $this.attr('skuId');
                var skuColor = $this.attr('skuColor');
                var skuSize = $this.attr('skuSize');
                $inStockModal.find('#inStockColor').html(skuColor);
                $inStockModal.find('#inStockSize').html(skuSize);
                $inStockModal.find('[name=skuId]').val(skuId);
            });
        }
    });

    var $dataTable = $('#itemSkuListTable').dataTable(options);

    $('#editItem').click(function() {
        yoyoResetForm2($editItemSkuForm);
    });
})