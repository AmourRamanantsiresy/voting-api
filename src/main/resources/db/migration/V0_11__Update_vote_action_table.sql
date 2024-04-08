alter table "vote_action"
    add if not exists
        otp_id varchar references otp (id);