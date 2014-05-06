class String
  ALPHABET = [*"A".."Z"]
end

def f(x)
  a = String::ALPHABET
  x < a.size ? a[x] : f(x / a.size - 1) + f(x % a.size)
end

puts "-----------------------------"

describe "column" do
  it "returns A to Z" do
    expect(f(0)).to eq "A"
    expect(f(1)).to eq "B"
    expect(f(25)).to eq "Z"
    expect(f(26)).to eq "AA"
    expect(f(26 + 1)).to eq "AB"
    expect(f(26 + 25)).to eq "AZ"
    expect(f(26 * 2)).to eq "BA"
    expect(f(26 * 2 + 25)).to eq "BZ"
    expect(f(26 * 26)).to eq "ZA"
    expect(f(26 * 26 + 25)).to eq "ZZ"
    expect(f(26 * 26 + 26)).to eq "AAA"
    expect(f(26 * 26 + 26 + 25)).to eq "AAZ"
    expect(f(26 * 26 + 26 * 2)).to eq "ABA"
    expect(f(26 * 26 + 26 * 26)).to eq "AZA"
    expect(f(26 * 26 + 26 * 26 + 25)).to eq "AZZ"
    expect(f(26 * 26 + 26 * 26 + 26)).to eq "BAA"
  end
end
