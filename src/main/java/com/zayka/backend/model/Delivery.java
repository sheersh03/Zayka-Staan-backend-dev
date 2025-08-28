package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Delivery {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private Long childId; private String date; private String routeName; private String status;
  private String deliveredAt; private String exceptionReason;
}