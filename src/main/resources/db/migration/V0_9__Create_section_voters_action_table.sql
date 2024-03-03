create table if not exists "section_voters_action"
(
    id              varchar
        constraint section_voters_action_pk primary key default uuid_generate_v4(),
    created_at      timestamp                           default current_timestamp,
    vote_section_id varchar not null,
    constraint fk_section_voters_action_vote foreign key (vote_section_id) references vote_section (id)
);