/**
 * Created by gezz on 2017/3/18.
 */
$(function(){
    var $editItemColorModal = $('#editItemColorModal');
    var $editItemColorForm = $('#editItemColorForm');
    $editItemColorForm.submit(function (e) {
        e.preventDefault();
        var submitData = $editItemColorForm.serializeObject();
        $.postJSON(
            baseUrl + '/sysops/color/edit_item_color',
            submitData,
            function(retJson) {
                if(retJson.errno == 0) {
                    yoyoResetForm2($editItemColorForm);
                    toastr.success('添加成功', '', {positionClass: 'toast-top-center'});
                    $editItemColorModal.modal('hide');
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
                    return source.name;
                },
            },
            {
                "data": function (source, type, val) {
                    return '<a editItemId="'+source.id+'" href="javascript:void(0);">编辑</a>';
                },
            },

        ],
        serverSide: true,
        ajax: {
            url: baseUrl + '/sysops/color/item_color_page_list',
            data: function (data) {
                //请求参数
                var filterData = $.extend({}, data, {});
                filterData['page'] = data['start'] / data['length'] + 1;
                filterData['pageColor'] = data['length'];
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
                    baseUrl + '/sysops/color/item_color_info',
                    {
                        itemColorId: itemId
                    },
                    function (retJson) {
                        if (retJson.errno == 0) {
                            var itemColor = retJson.data;
                            $editItemColorModal.modal('show');
                            $editItemColorModal.find('[name=id]').val(itemColor.id);
                            $editItemColorModal.find('[name=name]').val(itemColor.name);
                        } else {
                            toastr.error(retJson.errmsg, '');
                        }
                    }
                )

            });
            $('#itemColorListTable_info').removeAttr('aria-live');
        }
    });

    var $dataTable = $('#itemColorListTable').dataTable(options);

    $('#editItemColor').click(function() {
        yoyoResetForm2($editItemColorForm);
    });
})