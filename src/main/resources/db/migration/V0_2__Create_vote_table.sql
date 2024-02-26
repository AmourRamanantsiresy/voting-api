create table if not exists "vote"
(
    id                   varchar
        constraint vote_pk primary key default uuid_generate_v4(),
    name                 varchar unique not null,
    voters_count_allowed int           default 100,
    voters_count         int           default 0,
    created_at           timestamp     default current_timestamp
);