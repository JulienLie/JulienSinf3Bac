link(a, b).
link(a, c).
link(c, d).
link(d, a).
path(X, Y) :- link(X, Y).
path(X, Y) :- link(X, N1), path(N1, Y).
pathList(X, Y, [X, Y]) :- link(X, Y).
pathList(X, Y, [X|L]) :- link(X, N1), pathList(N1, Y, L).
