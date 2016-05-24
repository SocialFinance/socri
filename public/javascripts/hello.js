if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

$(document).ready(function() {
  $.get('/tasks', function(data) {
    data.forEach(function(e) {
      $('#tasks').append($("<li>").text(e.contents));
    });
  });
});
