package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
  Optional<Otp> getOtpByValue(String value);

  boolean existsByValue(String value);
}
