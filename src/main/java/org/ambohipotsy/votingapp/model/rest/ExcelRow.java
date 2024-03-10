package org.ambohipotsy.votingapp.model.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExcelRow {
  private int row;
  private List<ExcelCell> cells;
}
