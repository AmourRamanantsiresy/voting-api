create table if not exists "otp"
(
    id              varchar
        constraint otp_pk primary key default uuid_generate_v4(),
    value varchar unique not null,
    is_already_used bool default false,
    created_at      timestamp                           default current_timestamp
);