create table if not exists "vote_section"
(
    id                 varchar
        constraint vote_section_pk primary key default uuid_generate_v4(),
    name               varchar not null,
    vote_count_allowed int                     default 1,
    created_at         timestamp               default current_timestamp,
    vote_id            varchar not null,
    constraint fk_vote_section_vote foreign key (vote_id) references vote (id)
);