module Test.Main where

import Prelude

import Test.Spec (describe, it)
import Test.Spec.Runner (run)
import Test.Spec.Assertions (shouldEqual)
import Test.Spec.Reporter.Console (consoleReporter)

import Some (add2)

main = run [consoleReporter] do
  describe "Simple" do
    it "add" do
      add2 1 1 `shouldEqual` 2
      add2 2 3 `shouldEqual` 5
