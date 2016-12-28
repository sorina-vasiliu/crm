function ErrorMessages(textbox) {
    if (textbox.validity.valueMissing) {
        textbox.setCustomValidity('Campul este obligatoriu.');
    }
    else if(textbox.validity.typeMismatch){
        textbox.setCustomValidity('Text invalid.');
    }
    else {
        textbox.setCustomValidity('');
    }
    return true;
}
function prepareData(data) {
    for (var i = 0; i < data.columns.length; i++) {
        var column = data.columns[i];
        column.searchRegex = column.search.regex;
        column.searchValue = column.search.value;
        delete(column.search);
    }
}


function getParameterValue(parameter){
    var lastIndex = window.location.search.lastIndexOf(parameter);
    if(lastIndex==-1)
        return null;
    /*lastIndex of the parameter + number of character + = */
    var length = lastIndex+parameter.length+1;
    return window.location.search.substr(length,window.location.search.length).split('&')[0];
}