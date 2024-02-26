create table if not exists "vote_action"
(
    id                varchar
        constraint vote_action_pk primary key default uuid_generate_v4(),
    created_at        timestamp               default current_timestamp,
    vote_candidate_id varchar not null,
    constraint fk_vote_action_vote_candidate foreign key (vote_candidate_id) references vote_candidate (id)
);