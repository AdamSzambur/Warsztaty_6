$(function () {
    $('div.card-footer').each(function (){

        if ($(this).data('error')===0) {
            $(this).hide();
        } else {
            $(this).show();
            $(this).prev().find('a.btn.btn-primary').hide();
        }
    })

    $('a.btn.btn-primary').on('click', function (even) {
        even.preventDefault();
        ($(this).parent().next()).show();
        $(this).hide();
    })
});