plays(fred, guitar). 
plays(barney, guitar). plays(barney, piano).
plays(wilma, flute). 
plays(betty, piano). plays(betty, flute).
plays(bam_bam, bass_drum).
plays(dino, piano).

enjoys(blues, dino). enjoys(blues, barney). enjoys(blues, wilma).
enjoys(jazz, wilma). enjoys(jazz, fred). 
enjoys(rock, bam_bam). 
enjoys(classical, dino). enjoys(classical, betty). enjoys(classical, barney). enjoys(classical, wilma).

piece(Genre, Instrument, Who) :- plays(Who, Instrument), enjoys(Genre, Who).

piece(Genre, Instrument) :- forall((plays(Who, Instrument), enjoys(Genre, Who)), writeln(Who)).

/*
?- plays(Who, guitar).
Who = fred ;
Who = barney ;
false.

?- piece(classical, piano, Who).
Who = barney ;
Who = betty ;
Who = dino ;
false.

?- piece(classical, Instrument, betty).
Instrument = piano ;
Instrument = flute ;
false.

?- piece(classical, Instrument, Who).
Instrument = guitar,
Who = barney ;
Instrument = piano,
Who = barney ;
Instrument = flute,
Who = wilma ;
Instrument = piano,
Who = betty ;
Instrument = flute,
Who = betty ;
Instrument = piano,
Who = dino ;
false.

*/
