var id = '1n1_O6XJUOCdzZ1NK2Lld3aTOATb3IsLmyHrz-eXKzJQ';
var url = 'https://spreadsheets.google.com/feeds/list/' + id + '/od6/public/values?alt=json';

axios.get(url)
  .then(function(response) {
    var items = response.data.feed.entry;
    var events = items.map(function(row) {
      var date = moment(row['gsx$date'].$t, 'YYYY. MM. DD');
      var author = row['gsx$author'].$t;
      var title = row['gsx$title'].$t;
      var url = row['gsx$url'].$t;
      if (!date.isValid() || !title) {
        return null;
      }
      url = url.match(/^http/) ? url : '';
      return {
        start: date.format('YYYY-MM-DD'),
        title: '[' + author + '] ' + title + (url ? '' : ' [예약]'),
        url: url
      };
    }).filter(function(event) { return event != null; });

    var element = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(element, {
      defaultDate: '2015-12-25',
      events: events,
      eventClick: function(info) {
        info.jsEvent.preventDefault();
        if (info.event.url) {
          window.open(info.event.url);
        }
      }
    });
    calendar.render();
  })
  .catch(function(error) {
    alert(error);
  });
