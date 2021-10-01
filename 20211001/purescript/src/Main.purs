module Main where

import Prelude

import Data.Maybe (Maybe(..))
import Data.String (trim)

import Effect (Effect)
import Effect.Console (log)

import Web.HTML (window)
import Web.HTML.Window (document)
import Web.HTML.HTMLDocument (HTMLDocument, toParentNode)

import Web.DOM.Element (Element, toNode)
import Web.DOM.Node (textContent)
import Web.DOM.ParentNode (QuerySelector(QuerySelector), querySelector)

select :: String -> HTMLDocument -> Effect (Maybe Element)
select selector doc =
  querySelector (QuerySelector selector) (toParentNode doc)

elementText :: Maybe Element -> Effect String
elementText (Just el) = textContent (toNode el)
elementText _ = pure ""

main :: Effect Unit
main = do
  log "Hello, world!"
  text <- window >>= document >>= select "#app" >>= elementText
  log (trim text)
