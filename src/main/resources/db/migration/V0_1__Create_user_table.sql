create
    extension if not exists "uuid-ossp";

create table if not exists "user"
(
    id         varchar
        constraint user_pk primary key default uuid_generate_v4(),
    username   varchar unique,
    password   text,
    created_at timestamp               default current_timestamp
);