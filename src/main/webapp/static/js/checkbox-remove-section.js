$(document).ready(function() {
    // on form submit
    $("#form").on('submit', function() {
        // to each unchecked checkbox
        $(this).find('input[type=checkbox]:not(:checked)').prop('checked', true).val('ACTUAL');
    })
})