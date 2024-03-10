package org.ambohipotsy.votingapp.utils;

import org.ambohipotsy.votingapp.model.rest.ExcelCell;
import org.ambohipotsy.votingapp.model.rest.ExcelRow;
import org.ambohipotsy.votingapp.model.rest.ExcelRowLabel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelStyles {

  public void setColumnWidth(Sheet sheet, int columnIndex, double characterWidth) {
    long widthInUnits = Math.round((characterWidth + 0.5) * 256);
    sheet.setColumnWidth(columnIndex, (int) widthInUnits);
  }

  public CellStyle generalTitle(Workbook workbook) {
    CellStyle generalTitle = workbook.createCellStyle();
    generalTitle.setFont(titleFont(workbook, 12));
    return generalTitle;
  }

  public CellStyle bigTitle(Workbook workbook) {
    CellStyle bigTitle = workbook.createCellStyle();
    bigTitle.setFont(titleFont(workbook, 14));
    bigTitle.setAlignment(HorizontalAlignment.CENTER);
    bigTitle.setVerticalAlignment(VerticalAlignment.CENTER);
    return bigTitle;
  }

  public XSSFFont titleFont(Workbook workbook, int size) {
    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
    font.setBold(true);
    font.setFontHeightInPoints((short) size);
    font.setFontName("Calibri");
    return font;
  }

  public void mergeCells(Sheet sheet, int rowIndex, int startColumn, int endColumn) {
    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, startColumn, endColumn));
  }

  public CellStyle fullBorder(Workbook workbook) {
    CellStyle borderStyle = workbook.createCellStyle();
    borderStyle.setBorderTop(BorderStyle.THIN);
    borderStyle.setBorderBottom(BorderStyle.THIN);
    borderStyle.setBorderLeft(BorderStyle.THIN);
    borderStyle.setBorderRight(BorderStyle.THIN);
    borderStyle.setFont(titleFont(workbook, 12));
    return borderStyle;
  }

  public CellStyle sideBorder(Workbook workbook) {
    CellStyle borderStyle = workbook.createCellStyle();
    borderStyle.setBorderLeft(BorderStyle.THIN);
    borderStyle.setBorderRight(BorderStyle.THIN);
    return borderStyle;
  }

  public CellStyle breakLine(Workbook workbook) {
    CellStyle breakLine = workbook.createCellStyle();
    breakLine.setFillForegroundColor(IndexedColors.BLACK.getIndex());
    breakLine.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return breakLine;
  }

  public CellStyle topBorder(Workbook workbook) {
    CellStyle borderStyle = workbook.createCellStyle();
    borderStyle.setBorderTop(BorderStyle.THIN);
    return borderStyle;
  }

  public void printExcelRowTitle(Sheet sheet, ExcelRowLabel excelRowTitle) {
    Row row = sheet.createRow(excelRowTitle.getRow());
    Cell cell = row.createCell(excelRowTitle.getCell());
    if (excelRowTitle.getCellStyle() != null) {
      cell.setCellStyle(excelRowTitle.getCellStyle());
    }
    cell.setCellValue(excelRowTitle.getLabel());
  }

  public void printExcelRowTitle(Sheet sheet, ExcelRow excelRow) {
    Row row = sheet.createRow(excelRow.getRow());
    for (ExcelCell excelCell : excelRow.getCells()) {
      Cell cell = row.createCell(excelCell.getCell());
      if (excelCell.getCellStyle() != null) {
        cell.setCellStyle(excelCell.getCellStyle());
      }
      cell.setCellValue(excelCell.getLabel());
    }
  }
}
