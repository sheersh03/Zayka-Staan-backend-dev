package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subscription {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private Long childId;
  @Enumerated(EnumType.STRING) private Plan planId;
  private String status; private String startDate; private String nextRenewal;
  private Integer price; private String currency;
}