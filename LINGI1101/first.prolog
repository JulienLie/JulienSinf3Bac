pere(terach, abraham).
pere(abraham, isaac).
pere(haram, lot).
mere(lot, isaac). 
grandpere(X, Z) :- pere(X, Y), pere(Y, Z).
grandpere(X, Z) :- pere(X, Y), mere(Y, Z).
