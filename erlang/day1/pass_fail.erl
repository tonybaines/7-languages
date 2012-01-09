-module(pass_fail).
-export([check/1]).

check({error,Message}) -> Message;
check(success) -> "success".
