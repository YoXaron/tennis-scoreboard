insert into players (name)
values ('Carlos Alcaraz'),
       ('Daniil Medvedev'),
       ('Novak Djokovic'),
       ('Rafael Nadal'),
       ('Jannik Sinner');

insert into matches (player1_id, player2_id, winner_id)
values (1, 2, 1),
       (3, 4, 4),
       (1, 3, 3),
       (2, 4, 2),
       (5, 1, 5),
       (2, 3, 3),
       (4, 5, 4),
       (1, 4, 1);