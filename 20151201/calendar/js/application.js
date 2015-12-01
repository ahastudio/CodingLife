var id = '1n1_O6XJUOCdzZ1NK2Lld3aTOATb3IsLmyHrz-eXKzJQ';
var url = 'https://spreadsheets.google.com/feeds/list/' + id + '/od6/public/values?alt=json';

$(function() {
  $.getJSON(url, function (json) {
    var events = [];
    json.feed.entry.each(function(row) {
      var date = moment(row['gsx$date'].$t)
      if (date) {
        var author = row['gsx$author'].$t;
        var title = row['gsx$title'].$t;
        var url = row['gsx$url'].$t;
        if (title) {
          events.push({
            title: '[' + author + '] ' + title,
            start: date.format('YYYY-MM-DD'),
            url: url
          });
        }
      }
    });

    $('#calendar').fullCalendar({
      events: events,
      eventClick: function(event) {
        if (event.url && event.url.match(/^http/)) {
          window.open(event.url, '_blank');
        }
        return false;
      }
    });
  });
});
