create table players
(
    id   serial primary key,
    name varchar(256) unique
);

create table matches
(
    id      serial primary key,
    player1 int not null,
    player2 int not null,
    winner  int not null,
    foreign key (player1) references players (id),
    foreign key (player2) references players (id),
    foreign key (winner) references players (id)
);