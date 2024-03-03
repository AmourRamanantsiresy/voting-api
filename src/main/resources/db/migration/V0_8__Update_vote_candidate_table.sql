alter table "vote_candidate"
    add if not exists
        first_name              varchar,
    add if not exists last_name varchar;