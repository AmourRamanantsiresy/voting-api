package org.ambohipotsy.votingapp.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "\"otp\"")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String value;
    private boolean isInValid;
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
