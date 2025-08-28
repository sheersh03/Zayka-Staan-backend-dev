package com.zayka.backend.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @Table(name="selection_tbl")
public class Selection {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private Long childId; private String date; private String status; private Long chosenItemId;
}