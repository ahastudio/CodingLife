class LineCounter
  def initialize(code)
    @code = code
  end

  def lines_count
    do_real
    real_code.split("\n").size
  end

  def do_real
    replace_string
    remove_block_comments
    remove_line_comments
    remove_blank_lines
  end

  def real_code
    @code
  end

  def remove_line_comments
    @code = @code.split("\n").map{|line| remove_line_comment(line)}.join("\n")
  end

  def remove_line_comment(line)
    index = line.index("//")
    index ? line[0...index] : line
  end

  def remove_block_comments
    @code = remove_block_comment(@code)
  end

  def remove_block_comment(code)
    if code.include?("/*")
      index1 = code.index("/*")
      index2 = code.index("*/")
      if index2.nil?
        remove_block_comment(code[0...index1])
      else
        remove_block_comment(code[0...index1] + code[index2 + 2..-1])
      end
    else
      code
    end
  end

  def remove_blank_lines
    @code = @code.split("\n").reject{|line| line.strip.empty?}.join("\n")
  end

  def replace_string
    @code = @code.gsub(/\".*\"/){|s| s.gsub("/*", "").gsub("*/", "")}
  end
end

puts "\n-------------------------------------------------"

sample1 = <<END
// This file contains 3 lines of code
public interface Dave {
    /**
     * count the number of lines in a file
     */
    int countLines(File inFile); // not the real signature!
}
END

real1 = "public interface Dave {\n    int countLines(File inFile); \n}"

sample2 = <<END
/*****
* This is a test program with 5 lines of code
*  \/* no nesting allowed!
//*****//***/// Slightly pathological comment ending...

public class Hello {
    public static final void main(String [] args) { // gotta love Java
        // Say hello
      System./*wait*/out./*for*/println/*it*/("Hello/*");
    }

}
END

real2 = "public class Hello {\n" +
  "    public static final void main(String [] args) { \n" +
  "      System.out.println(\"Hello\");\n    }\n}"

sample3 = <<END
/* this is " */ int a = 3; /* " */
END

describe LineCounter do
  describe "with sample code" do
    it "couts code lines" do
      LineCounter.new(sample1).lines_count.should == 3
      LineCounter.new(sample2).lines_count.should == 5
      #LineCounter.new(sample3).lines_count.should == 1
    end

    it "makes real code and counting code lines" do
      inputs = [sample1, sample2]
      outputs = [real1, real2]
      inputs.zip(outputs).each do |input, output|
        subject = LineCounter.new(input)
        subject.do_real
        subject.real_code.should == output
      end
    end
  end

  describe "#remove_line_comments" do
    it "remove line comments" do
      inputs = ["// xyz", " abc // xyz \nzzz"]
      outputs = ["", " abc \nzzz"]
      inputs.zip(outputs).each do |input, output|
        subject = LineCounter.new(input)
        subject.remove_line_comments
        subject.real_code.should == output
      end
    end
  end

  describe "#remove_block_comments" do
    subject { LineCounter.new(" /* abc */ zzz/* ... */") }

    it "remove block comments" do
      subject.remove_block_comments
      subject.real_code.should == "  zzz"
    end
  end

  describe "#replace_string" do
    subject { LineCounter.new("\"/* abc */\"") }

    it "remove block comment mark" do
      subject.replace_string
      subject.real_code.should == "\" abc \""
    end
  end
end
