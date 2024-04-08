alter table "voters_action"
    add if not exists
        otp_id varchar references otp (id);