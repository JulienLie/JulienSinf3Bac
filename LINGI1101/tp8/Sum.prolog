val(0, Y) :- Y is 0.
val(s(0), Y) :- Y is 1.
val(s(s(X)), Y) :- val(s(X), Y1), Y is Y1+1.
negative(X, Y) :- val(X, Y1), Y is -Y1.
sum(X, Y, Z) :- val(X, Y1), val(Y, Y2), Z is Y1+Y2.
