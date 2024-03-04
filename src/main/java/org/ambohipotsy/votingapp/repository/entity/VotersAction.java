package org.ambohipotsy.votingapp.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "\"voters_action\"")
public class VotersAction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "vote_id")
  private Vote vote;
}
