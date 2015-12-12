require 'rails_helper'

# Specs in this file have access to a helper object that includes
# the PostsHelper. For example:
#
# describe PostsHelper do
#   describe "string concat" do
#     it "concats two strings with spaces" do
#       expect(helper.concat_strings("this","that")).to eq("this that")
#     end
#   end
# end
RSpec.describe PostsHelper, type: :helper do
  describe '#post_body_to_html' do
    it 'convert raw text to HTML' do
      expect(helper.post_body_to_html("A\nB\n\nC"))
        .to eq("<p>A\n<br />B</p>\n\n<p>C</p>")
    end
  end
end
