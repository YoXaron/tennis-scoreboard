create table players
(
    id   bigserial primary key,
    name varchar(256) not null unique
);

create table matches
(
    id         bigserial primary key,
    player1_id int not null,
    player2_id int not null,
    winner_id  int not null,
    foreign key (player1_id) references players (id),
    foreign key (player2_id) references players (id),
    foreign key (winner_id) references players (id)
);