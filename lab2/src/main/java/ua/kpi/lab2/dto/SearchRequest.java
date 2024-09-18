package ua.kpi.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

  private String searchRequestPrefixes;
  private boolean searchByAuthor;
  private boolean searchByKeywords;
}