-module(splitter).
-export([word_count/1]).

word_count([]) -> 0;
word_count([$ ]) -> 0; %% A space on its own
word_count([_]) -> 1; %% A character on its own
word_count([$ |Rest]) -> 0 + word_count(Rest); %% space followed by something else
word_count([_|[$ |Rest]]) -> 1 + word_count(Rest); %% end of word, followed by a space
word_count([_|Rest]) -> 0 + word_count(Rest). %% >=2 non-space chars (mid-word)
