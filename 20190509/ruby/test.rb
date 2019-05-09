# frozen_string_literal: true

MVP = "
2017-18     James Harden, Houston Rockets
2016-17     Russell Westbrook, Oklahoma City Thunder
2015-16     Stephen Curry, Golden State Warriors
2014-15     Stephen Curry, Golden State Warriors
2013-14     Kevin Durant, Oklahoma City Thunder
2012-13     LeBron James, Miami Heat
2011-12     LeBron James, Miami Heat
2010-11     Derrick Rose, Chicago Bulls
2009-10     LeBron James, Cleveland Cavaliers
2008-09     LeBron James, Cleveland Cavaliers
2007-08     Kobe Bryant, Los Angeles Lakers
2006-07     Dirk Nowitzki, Dallas Mavericks
2005-06     Steve Nash, Phoenix Suns
2004-05     Steve Nash, Phoenix Suns
2003-04     Kevin Garnett, Minnesota Timberwolves
2002-03     Tim Duncan, San Antonio Spurs
2001-02     Tim Duncan, San Antonio Spurs
2000-01     Allen Iverson, Philadelphia 76ers
1999-00     Shaquille O'Neal, Los Angeles Lakers
1998-99     Karl Malone, Utah Jazz
1997-98     Michael Jordan, Chicago Bulls
1996-97     Karl Malone, Utah Jazz
1995-96     Michael Jordan, Chicago Bulls
1994-95     David Robinson, San Antonio Spurs
1993-94     Hakeem Olajuwon, Houston Rockets
1992-93     Charles Barkley, Phoenix Suns
1991-92     Michael Jordan, Chicago Bulls
1990-91     Michael Jordan, Chicago Bulls
1989-90     Magic Johnson, Los Angeles Lakers
1988-89     Magic Johnson, Los Angeles Lakers
1987-88     Michael Jordan, Chicago Bulls
1986-87     Magic Johnson, Los Angeles Lakers
1985-86     Larry Bird, Boston Celtics
1984-85     Larry Bird, Boston Celtics
1983-84     Larry Bird, Boston Celtics
1982-83     Moses Malone, Philadelphia 76ers
1981-82     Moses Malone, Houston Rockets
1980-81     Julius Erving, Philadelphia 76ers
1979-80     Kareem Abdul-Jabbar, Los Angeles Lakers
1978-79     Moses Malone, Houston Rockets
1977-78     Bill Walton, Portland Trail Blazers
1976-77     Kareem Abdul-Jabbar, Los Angeles Lakers
1975-76     Kareem Abdul-Jabbar, Los Angeles Lakers
1974-75     Bob McAdoo, Buffalo Braves
1973-74     Kareem Abdul-Jabbar, Milwaukee Bucks
1972-73     Dave Cowens, Boston Celtics
1971-72     Kareem Abdul-Jabbar, Milwaukee Bucks
1970-71     Kareem Abdul-Jabbar, Milwaukee Bucks
1969-70     Willis Reed, New York Knicks
1968-69     Wes Unseld, Baltimore Bullets
1967-68     Wilt Chamberlain, Philadelphia 76ers
1966-67     Wilt Chamberlain, Philadelphia 76ers
1965-66     Wilt Chamberlain, Philadelphia 76ers
1964-65     Bill Russell, Boston Celtics
1963-64     Oscar Robertson, Cincinnati Royals
1962-63     Bill Russell, Boston Celtics
1961-62     Bill Russell, Boston Celtics
1960-61     Bill Russell, Boston Celtics
1959-60     Wilt Chamberlain, Philadelphia Warriors
1958-59     Bob Pettit, St. Louis Hawks
1957-58     Bill Russell, Boston Celtics
1956-57     Bob Cousy, Boston Celtics
1955-56     Bob Pettit, St. Louis Hawks
"

module Enumerable
  def tally
    group_by(&:itself).each_with_object({}) { |e, o| k, v = e; o[k] = v.size }
  end
end

def main
  mvps = MVP.split(/\n/).reject(&:empty?)
  names = mvps.map { |i| i.match(/\w\s+(.*),.*/)[1] }
  frequencies = names.tally.sort_by(&:last).reverse.map do |name, frequency|
    puts "#{name} (#{frequency})"
    frequency
  end
  puts "Total: #{mvps.size} = #{frequencies.sum}"
end

main
