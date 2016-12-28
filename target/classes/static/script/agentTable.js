$(document).ready(function(){
    var table = $('#agentTable').DataTable({
        "serverSide":true,
        "ajax":{
            "url":"/filterAgents",
            "data": function(data) {
                var warehouseId = getParameterValue("warehouseId");
                if(warehouseId != null) {
                    data.warehouseId = warehouseId;
                }
                prepareData(data);
            }
        },
        "columnDefs": [
            { "name" : "id","data":"id","targets":0,"visible":false,  "sortable":false, "searchable":false},
            { "name" : "warehouse.id","data":"warehouseId","targets":1,"visible":false, "sortable":false, "searchable":false},
            { "name" : "name","data":"name","targets":2,"sClass":"text-center"},
            { "name" : "mail","data":"mail","targets":3,"sClass":"text-center"},
            { "name" : "warehouse.name","data":"warehouseName","targets":4,"sClass":"text-center",
                "render": function (data, type, full, meta ) {
                    return type === 'display'?
                    '<a href="./warehouseDetails?id='+full.warehouseId+'">'+data+'</a>' : data;
                }},
            { "data":null,"targets":-1,"sortable":false, "searchable":false, "defaultContent": "<button class='btn details-button'>Detalii</button>","sClass":"text-center"}
        ],
        "language": {
            "url": "/json/Romanian.json"
        },
        "info": false,
        "sPaginationType": "full_numbers",
        preDrawCallback: function (settings) {
            var api = new $.fn.dataTable.Api(settings);
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');

            if (api.page.info().pages <= 1) {
                pagination.hide();
            }
            else {
                pagination.show();
            }
        }
    });
    $('#agentTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr') ).data();
        window.location ="./agentDetails?id="+data.id;
    } );
});
