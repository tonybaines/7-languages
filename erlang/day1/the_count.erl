-module(the_count).
-export([loves_to_count/1]).

loves_to_count(0) -> io:format("Ah ha ha!");
loves_to_count(N) -> loves_to_count(N-1), io:format("~B~n",[N]). %% Why is printing a number so *hard* ?

