# ExUnit 단위테스트
# $ elixir test.exs

ExUnit.start

defmodule MyTest do
  use ExUnit.Case

  test "the truth" do
    assert true
    assert 2 == 1 + 1
  end
end
