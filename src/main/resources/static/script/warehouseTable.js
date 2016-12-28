$(document).ready(function(){
    var table = $('#warehouseTable').DataTable({
        "serverSide":true,
        "ajax":{
            "url":"/filterWarehouses",
            "data": function(data) {
                    prepareData(data);
            }
        },
        "columnDefs": [
            { "name" : "id","data":"id","targets":0,"visible":false,  "sortable":false, "searchable":false},
            { "name" : "name","data":"name","targets":1,"sClass":"text-center"},
            { "name" : "mail","data":"mail","targets":2,"sClass":"text-center"},
            { "name" : "locality.name","data":"location","targets":3,"sClass":"text-center"},
            { "data" : null,"targets" : -1,"sortable":false, "searchable":false, "defaultContent": "<button class='btn details-button'>Detalii</button>",
                "sClass":"text-center"}
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
    $('#warehouseTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr') ).data();
        window.location ="./warehouseDetails?id="+data.id;
    } );
});
