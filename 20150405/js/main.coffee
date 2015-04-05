responsesCount = null

checkUpdate = ->
  Tabletop.init
    key: '1ZpK8G6wYlEEIOSVou_8rUInIDE7NgmIvOJ1XRfs6wuw'
    simpleSheet: true
    callback: (data, tabletop) ->
      responsesCount ?= data.length
      if data.length > responsesCount
        $('audio').get(0).play()
        $('.go-go-lets-go').stop()
          .slideDown()
          .animate({opacity: 1.0}, 1000 * 5)
          .slideUp()
      responsesCount = data.length

$ ->
  $('.go-go-lets-go').hide()
  setInterval ->
    checkUpdate()
  , 1000 * 3
