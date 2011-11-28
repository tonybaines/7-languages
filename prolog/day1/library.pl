author('Ian M. Banks').
author('William Gibson').
author('Neil Stephenson').
author('Stephen Donaldson').
author('Terry Pratchet').


wrote('Excession', 'Ian M. Banks').
wrote('Neuromancer', 'William Gibson').
wrote('Snow Crash', 'Neil Stephenson').
wrote('Chronicles of Thomas Covenant', 'Stephen Donaldson').
wrote('Count Zero', 'William Gibson').
wrote('Player Of Games', 'Ian M. Banks').
wrote('Thief Of Time', 'Terry Pratchet').

written_by(Author, Book) :- wrote(Book, Author).

/* 
?- author(Who).
Who = 'Ian M. Banks' ;
Who = 'William Gibson' ;
Who = 'Neil Stephenson' ;
Who = 'Stephen Donaldson' ;
Who = 'Terry Pratchet'.

?- wrote(What, 'Ian M. Banks').
What = 'Excession' ;
What = 'Player Of Games' ;
false.

?- written_by('Stephen Donaldson', What).
What = 'Chronicles of Thomas Covenant' ;
false.


*/
