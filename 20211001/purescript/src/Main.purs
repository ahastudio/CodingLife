module Main where

import Prelude

import Effect (Effect)
import Effect.Console (log)

greeting :: String -> String
greeting name =
  "Hello, " <> name <> "!"
