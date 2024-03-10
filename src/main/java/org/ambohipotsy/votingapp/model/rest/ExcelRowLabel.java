package org.ambohipotsy.votingapp.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.poi.ss.usermodel.CellStyle;

@Getter
@Builder
@AllArgsConstructor
public class ExcelRowLabel {
  private String label;
  private int row;
  private int cell;
  private CellStyle cellStyle;
}
