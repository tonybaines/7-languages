smallest([A],A). /*The smallest item in a single-item-list is the item*/
smallest([A,B], A) :- A =< B. /*In a list of two items the first is smallest if it is <= the second*/
smallest([A,B], B) :- A > B. /*In a list of two items the second is smallest if it is > the first*/
smallest([Head|Tail], X) :- smallest(Tail, X). /*The smallest item in a list is the smallest item in the tail of a list*/