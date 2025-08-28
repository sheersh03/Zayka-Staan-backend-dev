package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String date; private String cohort; private String title; private String items;
  private Integer kcal; private Integer protein; private Integer carbs; private Integer fat;
  private String allergens; private String theme;
}