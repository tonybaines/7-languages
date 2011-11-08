rev([],[]).
/*
there exists a list X which is the result of appending 
the Head of the original list to the reversed Tail of the original list 
*/
rev([Head|Tail],X) :- rev(Tail,Temp), append(Temp,[Head],X).
