rev([],[]).
rev([Head|Tail],X) :- rev(Tail,Temp), append(Temp,[Head],X).
