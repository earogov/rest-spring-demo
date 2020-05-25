INSERT INTO authors VALUES ('1', 'Peter Watts');
INSERT INTO authors VALUES ('2', 'Greg Egan');

INSERT INTO books VALUES (
'1',
'111-222-333',
'Quarantine',
'In 2034, the stars went out. An unknown agency surrounded the solar system with an impenetrable barrier, concealing the universe from humanity’s gaze.'
);

INSERT INTO books VALUES (
'2',
'111-222-444',
'Permutation city',
'Paul Durham keeps making Copies of himself: software simulations of his own brain and body which can be run in virtual reality, albeit seventeen times more slowly than real time.'
);

INSERT INTO books VALUES (
'3',
'222-333-444',
'Blindsight',
'In the year 2082, thousands of large, coordinated objects of an unknown origin, dubbed "Fireflies", burn up in the Earth atmosphere in a precise grid, while momentarily broadcasting across an immense portion of the electromagnetic spectrum, catching humanity off guard and alerting it to an undeniable extraterrestrial presence. It is suspected that the entire planet has been surveyed in one effective sweep.'
);

INSERT INTO book_authors VALUES ('3', '1');
INSERT INTO book_authors VALUES ('1', '2');
INSERT INTO book_authors VALUES ('2', '2');