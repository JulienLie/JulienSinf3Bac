sum(0, Y, Y).
sum(s(X), Y, R) :- sum(X, s(Y), R).

sou(0, Y, negative(Y)).
sou(X, 0, X).
sou(s(X), s(Y), Z) :- sou(X, Y, Z).
