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

$(document).ready(function() {
    $("#accordion").accordion({
        animate: 0
    });
    $(".summernote").summernote({
        toolbar: toolbar,
        focus: true,
        height: 300,
        minHeight: null,
        maxHeight: null,
        lang: 'ro-RO'
    });
    $(".ui-helper-hidden-accessible").remove();
    $(".note-popover").remove();
});