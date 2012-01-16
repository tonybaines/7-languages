-module(shopping).
-export([price/1]).
-include_lib("eunit/include/eunit.hrl").

price(Cart) -> [{Product, Qty*Price} || {Product, Qty, Price} <- Cart].

price_a_cart_test() -> [{pencil,1.0},{pen,1.2},{paper,0.4}] = price([{pencil,4,0.25},{pen,1,1.2},{paper,2,0.2}]).
