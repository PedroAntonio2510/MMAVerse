CREATE TABLE contender(
    id serial primary key,
    name varchar(100) not null,
    nickname varchar(50) not null,
    cpf varchar(11) not null unique,
    date_of_birth date not null,
    age smallint,
    weight_class varchar(50) not null,
    height decimal not null,
    reach decimal not null,
    win smallint default 0,
    lose smallint default 0,
    draw smallint default 0,
    created_at timestamp,
    updated_at timestamp
);