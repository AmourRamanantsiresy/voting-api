package org.ambohipotsy.votingapp.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "\"vote\"")
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String name;
  private boolean isDone;
  private int votersCountAllowed;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
