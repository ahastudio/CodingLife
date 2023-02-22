package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
	"os/exec"
	"syscall"
	"time"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/canvas"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/driver/desktop"
	"fyne.io/fyne/v2/layout"
	"fyne.io/fyne/v2/widget"
)

func main() {
	app := app.New()

	driver, ok := app.Driver().(desktop.Driver)
	if !ok {
		// TODO: error
		return
	}

	win := driver.CreateSplashWindow()

	r, err := fyne.LoadResourceFromURLString("https://picsum.photos/500/300")
	if err != nil {
		panic(err)
	}

	image := canvas.NewImageFromResource(r)
	image.SetMinSize(fyne.NewSize(500, 300))
	image.FillMode = canvas.ImageFillOriginal

	text := widget.NewLabel("Hello World!")

	layout := container.New(layout.NewVBoxLayout(), image, text)

	go func() {
		filename := "download/test"
		download(filename)
		time.Sleep(time.Second * 2)
		rename(filename)
		launch()
		// os.Exit(0)
	}()

	win.SetContent(layout)
	win.ShowAndRun()
}

func download(filename string) {
	file, err := os.Create(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	response, err := http.Get("https://picsum.photos/1024")
	if err != nil {
		panic(err)
	}
	defer response.Body.Close()

	size, err := io.Copy(file, response.Body)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Download: %d bytes\n", size)
}

func rename(filename string) {
	// 같은 이름의 파일이 있다면 에러를 내야 할 것 같지만,
	// 실제로는 그냥 기존 파일에 덮어 쓰게 됨.
	os.Rename(filename, "download/test.jpg")
}

func launch() {
	binary, err := exec.LookPath("ls")
	if err != nil {
		panic(err)
	}

	args := []string{"ls", "-a", "-l", "-h"}

	env := os.Environ()

	err = syscall.Exec(binary, args, env)
	if err != nil {
		panic(err)
	}
}
