$(function() {
  var stoneMap = {
    O: 'white',
    X: 'black'
  };

  function drawBoard(rows) {
    var table = $('<table>');
    for (var i = 0; i < 8; i++) {
      var row = $('<tr>');
      for (var j = 0; j < 8; j++) {
        var cell = $('<td>');
        var it = stoneMap[rows[i][j]];
        if (it) {
          var stone = $('<span>').addClass('stone-' + it).text(it);
          cell.append(stone);
        }
        row.append(cell);
      }
      table.append(row);
    }
    $('#board').html(table);
  }

  function update() {
    $.getJSON('/board')
    .done(function(data) {
      var rows = data.board.split('\n');
      drawBoard(rows);
    })
    .error(function() {
      console.log('Error!');
    });
  }

  setInterval(update, 500);
  update();
});
