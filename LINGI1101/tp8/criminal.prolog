criminal(X) :- american(X), sells(X, Y, Z), weapon(Y), hostile(Z).
owns(nono, m1).
missile(m1).
sells(west, X, nono) :- missile(X), owns(nono, X).
weapon(X) :- missile(X).
hostile(X) :- enemy(X, america).
american(west).
enemy(nono, america).
