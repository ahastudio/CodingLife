package main

import (
	"github.com/pwiecz/go-fltk"
)

type App struct {
	win         *fltk.Window
	label       *fltk.Box
	progressBar *fltk.Progress

	message  string
	progress int
}

func NewApp() *App {
	fltk.InitStyles()

	win := createWindow()
	createImage()
	progressBar := createProgress()
	label := createLabel()

	app := &App{
		win: win, label: label, progressBar: progressBar,
		message: "", progress: 0,
	}

	app.setUp()

	return app
}

func (app *App) setUp() {
	var callback func()
	callback = func() {
		app.update()
		fltk.RepeatTimeout(0.1, callback)
	}
	fltk.AddTimeout(0, callback)
}

func (app *App) update() {
	app.label.SetLabel(app.message)
	app.progressBar.SetValue(float64(app.progress))
}

func (app *App) SetMessage(message string) {
	app.message = message
}

func (app *App) SetProgress(progress int) {
	app.progress = progress
}

func (app *App) Run() {
	app.win.End()
	app.win.Show()

	fltk.Run()
}
