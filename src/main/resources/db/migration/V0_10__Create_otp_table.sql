create table if not exists "otp"
(
    id          varchar
        constraint otp_pk primary key default uuid_generate_v4(),
    value       varchar unique not null,
    is_in_valid bool                  default false,
    created_at  timestamp             default current_timestamp,
    vote_id     varchar        not null,
    constraint otp_vote foreign key (vote_id) references vote (id)
);