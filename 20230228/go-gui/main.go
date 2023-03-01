package main

import (
	"fmt"
	"os"
	"time"
)

func main() {
	app := NewApp()

	go func() {
		app.SetMessage("Initialize...")

		time.Sleep(time.Second)

		for i := 0; i <= 100; i += 1 {
			time.Sleep(time.Millisecond * 100)

			app.SetMessage(fmt.Sprintf("Progress %d%%", i))
			app.SetProgress(i)

			if i == 100 {
				time.Sleep(time.Second)
				os.Exit(0)
				return
			}
		}
	}()

	app.Run()
}
