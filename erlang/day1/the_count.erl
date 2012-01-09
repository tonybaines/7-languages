-module(the_count).
-export([loves_to_count/1]).

loves_to_count(0) -> io:format("");
loves_to_count(N) -> loves_to_count(N-1), io:format(integer_to_list(N)++"~n"). %% Why is printing a number so *hard* ?

