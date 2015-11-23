describe "Lambda" do
  it "calls a lambda" do
    expect(-> x { x + 1 }[2]).to eq(3)
  end
end
