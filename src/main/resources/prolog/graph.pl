:- module(graphlib, [graphical_sequence/1]).

graphical_sequence(Degrees) :-
    exclude(=(0), Degrees, NonZero),
    (   NonZero = []
    ->  true
    ;   msort(NonZero, Sorted),
        reverse(Sorted, [D|Rest]),
        length(Rest, Len),
        D =< Len,
        take_prefix(D, Rest, Take, Tail),
        dec_each(Take, DecTake),
        append(DecTake, Tail, NewList),
        \+ (member(X, DecTake), X < 0),
        graphical_sequence(NewList)
    ).

take_prefix(0, Rest, [], Rest) :- !.
take_prefix(N, [H|T], [H|Take], RestTail) :-
    N > 0,
    N1 is N - 1,
    take_prefix(N1, T, Take, RestTail).


dec_each([], []).
dec_each([X|Xs], [Y|Ys]) :-
    Y is X - 1,
    dec_each(Xs, Ys).

%algorytm havvei hakimi