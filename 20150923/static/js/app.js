$(function() {
  function drawBoard() {
    var table = $('<table>');
    for (var i = 0; i < 8; i++) {
      var row = $('<tr>');
      for (var j = 0; j < 8; j++) {
        row.append('<td></td>');
      }
      table.append(row);
    }
    $('#board').html(table);
  }

  drawBoard();
});
