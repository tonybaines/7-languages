-module(lookup).
-export([key/2]).
-include_lib("eunit/include/eunit.hrl").

key(Key, Map) -> [Value || {Name, Value} <- Map, Key == Name].

key_lookup_with_one_map_entry_test() -> ["an OO language"] = key(ruby, [{ruby, "an OO language"}]).
key_lookup_with_two_map_entries_test() -> ["an OO language"] = key(ruby, [{erlang, "a functional language"}, {ruby, "an OO language"}]).
key_lookup_with_two_map_entries_the_other_key_test() -> ["a functional language"] = key(erlang, [{erlang, "a functional language"}, {ruby, "an OO language"}]).
