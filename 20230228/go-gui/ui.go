package main

import (
	"github.com/pwiecz/go-fltk"
)

const WIDTH = 400
const HEIGHT = 200
const BAR_HEIGHT = 30

func createWindow() *fltk.Window {
	win := fltk.NewWindow(WIDTH, HEIGHT)
	win.SetLabel("Demo")
	win.SetModal()

	return win
}

func createImage() {
	box := fltk.NewBox(fltk.FLAT_BOX, 0, 0, WIDTH, HEIGHT, "")
	image, err := fltk.NewJpegImageLoad("assets/image.jpg")
	if err != nil {
		panic(err)
	}
	box.SetImage(image)
}

func createProgress() *fltk.Progress {
	progress := fltk.NewProgress(
		5, HEIGHT-BAR_HEIGHT-5, WIDTH-5*2, BAR_HEIGHT)
	progress.SetMaximum(100)
	progress.SetSelectionColor(0x42A5F500)

	return progress
}

func createLabel() *fltk.Box {
	label := fltk.NewBox(fltk.NO_BOX,
		5, HEIGHT-BAR_HEIGHT-5, WIDTH-5*2, BAR_HEIGHT, "")
	label.SetLabelSize(18)

	return label
}
