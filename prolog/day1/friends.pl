/*
# Excerpted from "Seven Languages in Seven Weeks",
# published by The Pragmatic Bookshelf.
# Copyrights apply to this code. It may not be used to create training material, 
# courses, books, articles, and the like. Contact us if you are in doubt.
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/btlang for more book information.
*/
likes(wallace, cheese).
likes(grommit, cheese).
likes(wendolene, sheep).
friend(X, Y) :- \+(X = Y), likes(X, Z), likes(Y, Z).
/*
This definition works when a variable is passd in as one of the parameters
X and Y are not bound when the equality check is done
friend(X, Y) :- likes(X, Z), likes(Y, Z), \+(X = Y).
*/
