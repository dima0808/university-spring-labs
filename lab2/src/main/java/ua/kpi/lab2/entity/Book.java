package ua.kpi.lab2.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  private Long id;
  private String title;
  private String author;
  private Integer year;
  private List<String> keywords;
}
