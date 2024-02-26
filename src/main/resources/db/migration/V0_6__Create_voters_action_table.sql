create table if not exists "voters_action"
(
    id         varchar
        constraint voters_action_pk primary key default uuid_generate_v4(),
    created_at timestamp                                default current_timestamp,
    vote_id    varchar not null,
    constraint fk_voters_action_vote foreign key (vote_id) references vote (id)
);