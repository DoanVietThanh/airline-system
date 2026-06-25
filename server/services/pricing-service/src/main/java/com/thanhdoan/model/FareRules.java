package com.thanhdoan.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class FareRules {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "rule_name", nullable = false)
  private String ruleName;

  @Column(name = "airline_id", nullable = false)
  private Long airlineId;

  @ManyToOne
  @Column(name = "fare", nullable = false)
  private Fare fare;

  @Column(name = "is_refundable", nullable = false)
  private Boolean isRefundable;

  @Column(name = "change_fee", nullable = false)
  private Double changeFee;

  @Column(name = "cancellation_fee", nullable = false)
  private Double cancellationFee;

  @Column(name = "refund_deadline_days", nullable = false)
  private Integer refundDeadlineDays;

  @Column(name = "change_deadline_hours", nullable = false)
  private Integer changeDeadlineHours;

  @Column(name = "is_changeable", nullable = false)
  private Boolean isChangeable;

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;
}
