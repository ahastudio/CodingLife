-- Elm test.

import Console exposing (IO, putStrLn)
import Task

hello : IO ()
hello = putStrLn "Hello, world!"

port runner : Signal (Task.Task x ())
port runner = Console.run hello
