package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private Long subscriptionId; private String periodStart; private String periodEnd;
  private Integer amount; private String status; private String method;
}