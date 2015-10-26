import './css/style.scss!';
import 'jquery';

$(document).on('mouseleave', 'p', () => {
  $('<span>').text('.').appendTo($('body'));
});
