package org.ambohipotsy.votingapp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.ExcelCell;
import org.ambohipotsy.votingapp.model.rest.ExcelRow;
import org.ambohipotsy.votingapp.model.rest.ExcelRowLabel;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.utils.ExcelStyles;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExcelService {
  private final ExcelStyles excelStyles;
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private final VoteService voteService;

  public byte[] getPv(String voteId) throws IOException {
    VoteResult voteResult = voteService.getResult(voteId);
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("PV");
    CellStyle generalTitleStyle = excelStyles.generalTitle(workbook);
    CellStyle bigTitleStyle = excelStyles.bigTitle(workbook);
    CellStyle fullBorderStyle = excelStyles.fullBorder(workbook);
    CellStyle borderSideStyle = excelStyles.sideBorder(workbook);
    CellStyle topSideStyle = excelStyles.topBorder(workbook);
    CellStyle breakLineStyle = excelStyles.breakLine(workbook);

    excelStyles.setColumnWidth(sheet, 0, 4.15);
    excelStyles.setColumnWidth(sheet, 1, 29.75);
    excelStyles.setColumnWidth(sheet, 2, 14.15);
    excelStyles.setColumnWidth(sheet, 3, 8.44);
    excelStyles.setColumnWidth(sheet, 4, 26.59);

    List<ExcelRowLabel> titles =
        List.of(
            new ExcelRowLabel("FIANGONANA JESOA KRISTY ETO MADAGASIKARA", 0, 1, generalTitleStyle),
            new ExcelRowLabel("SYNODAMPARITANY ANTANANARIVO ATSIMO", 1, 1, generalTitleStyle),
            new ExcelRowLabel(
                "FITANDREMANA RASALAMA MARITIORA AMBOHIPOTSY", 2, 1, generalTitleStyle),
            new ExcelRowLabel("RAKI-TSORATRY NY FIFIDIANANA", 5, 1, bigTitleStyle));

    for (ExcelRowLabel ExcelRowLabel : titles) {
      excelStyles.printExcelRowTitle(sheet, ExcelRowLabel);
      excelStyles.mergeCells(sheet, ExcelRowLabel.getRow(), 1, 4);
    }

    AtomicInteger lastRow = new AtomicInteger(7);

    voteResult
        .getSectionResults()
        .forEach(
            voteSectionResult -> {
              List<ExcelRow> voteSectionInfo =
                  List.of(
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("SAMPANA:", 1, generalTitleStyle),
                              new ExcelCell(voteSectionResult.getName(), 2, null))),
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("DATY:", 1, generalTitleStyle),
                              new ExcelCell(
                                  voteResult.getCreatedAt().format(dateFormatter), 2, null))),
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("ISAN'NY MPIFIDY:", 1, generalTitleStyle),
                              new ExcelCell(voteResult.getVotersCount().toString(), 2, null))),
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("VATO MANANKERY:", 1, generalTitleStyle),
                              new ExcelCell(
                                  voteSectionResult.getVotersCount().toString(), 2, null))),
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("VATO FOTSY:", 1, generalTitleStyle),
                              new ExcelCell(
                                  voteSectionResult.getWhiteVoteCount().toString(), 2, null))),
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("FIFIDIANANA:", 1, generalTitleStyle),
                              new ExcelCell(voteSectionResult.getName(), 2, null))));

              voteSectionInfo.forEach(
                  excelRow -> {
                    excelStyles.printExcelRowTitle(sheet, excelRow);
                  });
              lastRow.addAndGet(2);

              List<String> tableHeader = List.of("N°", "KANDIDA", "VATO AZO", "%", "FANAMARIHANA");
              Row row = sheet.createRow(lastRow.get());

              for (int i = 0; i < tableHeader.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(fullBorderStyle);
                cell.setCellValue(tableHeader.get(i));
              }

              voteSectionResult
                  .getVoteCandidateResults()
                  .forEach(
                      voteCandidateResult -> {
                        ExcelRow tableValue =
                            new ExcelRow(
                                lastRow.addAndGet(1),
                                List.of(
                                    new ExcelCell("", 0, borderSideStyle),
                                    new ExcelCell(
                                        voteCandidateResult.getName(), 1, borderSideStyle),
                                    new ExcelCell(
                                        voteCandidateResult.getVotes().toString(),
                                        2,
                                        borderSideStyle),
                                    new ExcelCell(
                                        String.valueOf(voteCandidateResult.getVotesInPercent()),
                                        3,
                                        borderSideStyle),
                                    new ExcelCell("", 4, borderSideStyle)));

                        excelStyles.printExcelRowTitle(sheet, tableValue);
                      });
              List<ExcelRow> tableEndValue =
                  List.of(
                      new ExcelRow(
                          lastRow.addAndGet(1),
                          List.of(
                              new ExcelCell("", 0, topSideStyle),
                              new ExcelCell("", 1, topSideStyle),
                              new ExcelCell("", 2, topSideStyle),
                              new ExcelCell("", 3, topSideStyle),
                              new ExcelCell("", 4, topSideStyle))),
                      new ExcelRow(
                          lastRow.addAndGet(2),
                          List.of(
                              new ExcelCell("", 0, breakLineStyle),
                              new ExcelCell("", 1, breakLineStyle),
                              new ExcelCell("", 2, breakLineStyle),
                              new ExcelCell("", 3, breakLineStyle),
                              new ExcelCell("", 4, breakLineStyle))));

              tableEndValue.forEach(excelRow -> excelStyles.printExcelRowTitle(sheet, excelRow));
              lastRow.addAndGet(2);
            });

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    workbook.close();

    return outputStream.toByteArray();
  }
}
