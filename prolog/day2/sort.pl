/*A sorted list is one where the head of the list is =< the head of the sorted remainder*/
sorted([],[]).
sorted([A],[A]).
sorted([A,B], [A,B]) :- A =< B.
sorted([A,B], [B,A]) :- A > B.

sorted([A|[B|C]], X) :- 
	A =< B, 
	append([B],C,Rest), 
	sorted(Rest,Temp), 
	append([A],Temp,X).
sorted([A|[B|C]], X) :- 
	A > B, 
	append([A],C,Rest), 
	sorted(Rest,Temp), 
	append([B],Temp,X).
