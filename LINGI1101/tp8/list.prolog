add([], L, L).
add([X|L1], L2, [X|L3]) :- append(L1, L2, L3).
remove([], L, []).
%remove([X|L1], L, [X|L3])
