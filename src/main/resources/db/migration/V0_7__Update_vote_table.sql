alter table "vote"
    add
        if not exists is_done bool default false;