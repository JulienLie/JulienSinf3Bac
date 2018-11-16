add([], L, [L]).
add([X|L1], L2, [X|L3]) :- add(L1, L2, L3).

removeFirst([X|L], L).

remove([], L, []).
remove([X|L1], E, L2) :- X == E, remove(L1, E, L2).
remove([X|L1], E, [X|L2]) :- X \= E, remove(L1, E, L2).

contains([X|L1], E) :- X == E.
contains([X|L1], E) :- X \= E, contains(L1, E).

sum([], 0).
sum([X|L], N) :- sum(L, N1), N is X+N1.

concat([], L, L).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

size([], 0).
size([X|L], N) :- size(L, N1), N is 1+N1.

same([], []).
same([X1|L1], [X1|L2]) :- same(L1, L2).

get([X|L], 0, X).
get([X|L], N, R) :- N1 is N-1, get(L, N1, R).
