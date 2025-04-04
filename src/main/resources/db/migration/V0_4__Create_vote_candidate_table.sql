create table if not exists "vote_candidate"
(
    id              varchar
        constraint vote_candidate_pk primary key default uuid_generate_v4(),
    name            varchar not null,
    picture         text,
    created_at      timestamp                    default current_timestamp,
    vote_section_id varchar        not null,
    constraint fk_vote_candidate_vote_section foreign key (vote_section_id) references vote_section (id)
);