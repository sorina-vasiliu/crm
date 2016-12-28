var toolbar = [
    // [groupName, [list of button]]
    ['fontname', ['fontname']],
    ['style', ['bold', 'italic', 'underline']],
    ['fontsize', ['fontsize']],
    ['font', ['strikethrough', 'superscript', 'subscript']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['insert', ['table', 'picture', 'link']],
    ['misc', ['fullscreen', 'undo', 'redo']],
];

function getHtml(data)
{
    var decoded = $('<div/>').html(data.html).text();
    var html = "<div id='element"+ data.id+ "' class='col-md-12 col-xs-12'>"+
        "<div id='summernote"+data.id+"'>"+decoded+"</div>"+
        "<div style='float: right'>" +
        "<button id='edit"+ data.id+"' class='btn btn-primary' onclick='edit("+ data.id +")'type='button'>Editeaza</button>" +
        "<button id='save"+ data.id+"' class='btn btn-primary' style='display:none' onclick='save("+ data.id +")'type='button'>Salveaza</button>" +
        "<button id='delete"+ data.id+"' class='btn btn-danger' onclick='deleteElement("+ data.id +")'type='button'>Sterge</button>" +
        "</div>"+
        "</div>";
    return html;
    
}
function onMoveElements(options)
{
    var usersId = [];
    $('#multiselect_to option').each(function() {
        usersId.push(parseInt($(this).val()));
    });

    if(usersId.length == 0){
        $("#wygwys-content").children().remove();
        return;
    }

    $.ajax({
        url : "./home/getHtmlContent",
        method : "GET",
        data : {usersId : usersId}
     }).success(function(data) {
        $("#wygwys-content").children().remove();
        $.each(data, function (i, val) {
            $("#wygwys-content").append(getHtml(data[i]));
        })
    })
        .error(function (request,status, error) {
            console.log("Request:" + request.error() + "Status:" + status + "Error:"+ error);
    });
}

function edit(id) {
    $("#summernote"+id).summernote({
        toolbar: toolbar,
        focus: true,
        height: 300,
        minHeight: null,
        maxHeight: null,
        lang: 'ro-RO'
    });
    $("#save"+id).show();
    $("#edit"+id).hide();
}

function save(id) {
    var makrup = $("#summernote"+id).summernote('code');
    $("#summernote"+id).summernote('destroy');
    $("#save"+id).hide();
    $("#edit"+id).show();
    $.ajax({
        url : "./home/updateWyswyg",
        method : "POST",
        data : {htmlForWysiwyg : makrup , id : id}
    }).success(function(data) {
            console.log(data);
        }).error(function (request,status, error) {
            console.log("Request:" + request.error() + "Status:" + status + "Error:"+ error);
        });
 }

function deleteElement(id) {
    $.ajax({
        url : "./home/delete",
        method : "POST",
        data : {id : id}
    }).success(function(data) {
        $("#wygwys-content").find("#element"+data).remove();
    }).error(function (request,status, error) {
        console.log("Request:" + request.error() + "Status:" + status + "Error:"+ error);
    });
}

$(document).ready(function() {
    $('#multiselect').multiselect({
        search: {
            left: '<input type="text" name="q" class="form-control" placeholder="Cauta..." />',
            right: '<input type="text" name="q" class="form-control" placeholder="Cauta..." />',
        },
        afterMoveToRight: function () {
            onMoveElements();
        },
        afterMoveToLeft: function () {
            onMoveElements();
        }
    });

    $('#summernote').summernote({
        toolbar: toolbar,
        focus: true,
        height: 300,
        minHeight: null,
        maxHeight: null,
        lang: 'ro-RO'
    });

    $("#save-btn").on('click',function(){
        $("#error-empty-text").hide();
        $("#error-user-not-selected").hide()

        var htmlForWysiwyg = $('#summernote').summernote('code');
        if (htmlForWysiwyg === "" || htmlForWysiwyg == null) {
            $("#error-empty-text").show();
            return;
        }
        var usersId = [];
        $('#multiselect_to option').each(function () {
            usersId.push(parseInt($(this).val()));
        });
        if (usersId.length == 0) {
            $("#error-user-not-selected").show();
            return;
        }
        ;
        $.ajax({
            url: "./home/addWyswyg",
            method: "POST",
            data: {htmlForWysiwyg: htmlForWysiwyg, usersId: usersId}
        }).success(function (data) {
            $('#summernote').summernote('code', "");
            $("#wygwys-content").prepend(getHtml(data));
        }).error(function (request, status, error) {
            console.log("Request:" + request.error() + "Status:" + status + "Error:" + error);
        });
    })
    $(".ui-helper-hidden-accessible").remove();
});