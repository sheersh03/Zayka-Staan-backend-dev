package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*; import java.util.List;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Child {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String guardianId; private String name; private String cohort; private String classLabel;
  @ElementCollection private List<String> dietaryPrefs;
  @ElementCollection private List<String> allergens;
}