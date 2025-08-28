package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Feedback {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private Long childId; private String date; private Integer rating; private String tags; private String comment;
}