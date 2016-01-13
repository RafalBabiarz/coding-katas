## Word wrap

This is a slightly modified version of WordWrap Kata originally proposed by Uncle Bob (http://thecleancoder.blogspot.com/2010/10/craftsman-62-dark-path.html).
The difference from original version is that we're trying to not break words when it's possible (same as it's done in notepad).

Scenario which can describe the difference:
Original version: wordWrap("wordword", 3) -> wor\nd wo\nrd
Proposed solution: wordWrap("wordword", 3) -> wor\nd\nwor\nd
