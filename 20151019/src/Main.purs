module Main where

import Prelude

import Control.Monad.Eff.Console

import Some (add2)

main = do
  log "1 + 1"
  log (show (add2 1 1))
