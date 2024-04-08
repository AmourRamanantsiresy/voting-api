package org.ambohipotsy.votingapp.repository;

import java.util.List;

import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.ambohipotsy.votingapp.repository.entity.VotersAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotersActionRepository extends JpaRepository<VotersAction, String> {
    List<VotersAction> findAllByVoteId(String voteId);

    void deleteAllByOtpValue(String value);
}
