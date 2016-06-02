
var deleteUserInitial = function() {
    $('#deleteUser').addClass('hidden');
    $('#confirmDelete').removeClass('hidden');
}

var deleteUserCancel = function() {
    $('#deleteUser').removeClass('hidden');
    $('#confirmDelete').addClass('hidden');
}

var deleteUserFoReal = function(id) {
    $.ajax({
        url: '/api/user/'+id,
        type: 'DELETE',
        success: function(result) {
            window.location.replace('http://tinyurl.com/2g9mqh');
        }, error: function (request, status, error) {
            alert(request.responseText);
        }
    });
}
