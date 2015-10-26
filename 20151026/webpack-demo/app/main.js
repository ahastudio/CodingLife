import './css/main.scss';

$(document).on('mouseleave', 'p', () => {
  $('<span>').text('.').appendTo($('body'));
});
