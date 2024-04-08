package org.ambohipotsy.votingapp.repository;

import java.util.List;

import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.ambohipotsy.votingapp.repository.entity.VoteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteActionRepository extends JpaRepository<VoteAction, String> {
    List<VoteAction> findAllByVoteCandidateId(String voteCandidateId);
    void deleteAllByOtpValue(String value);
}
