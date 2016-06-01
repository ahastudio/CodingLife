$(document).on('submit', 'form', function() {
  var groups = [];
  $('.group').each(function(index, element) {
    var group = {
      name: $(element).find('[name=name]').val(),
      items: []
    };
    $(element).find('.item').each(function(index, element) {
      var item = {
        name: $(element).find('[name=name]').val(),
        price: $(element).find('[name=price]').val()
      };
      group.items.push(item);
    });
    groups.push(group);
  });
  var json = JSON.stringify(groups);
  console.log(json);
  return false;
});
