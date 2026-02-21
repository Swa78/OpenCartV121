package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtilities {

    FileInputStream fi;
    FileOutputStream fo;
    XSSFWorkbook wb;
    XSSFSheet ws;
    XSSFRow row;
    XSSFCell cell;
    CellStyle style;
    String path;

    // Constructor
    public ExcelUtilities(String path) {
        this.path = path;
    }

    // ✅ Get number of rows (CORRECT)
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        int rowcount = ws.getLastRowNum() + 1;

        wb.close();
        fi.close();
        return rowcount;
    }

    // ✅ Get number of columns
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();

        wb.close();
        fi.close();
        return cellcount;
    }

    // ✅ Read cell data safely
    public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rownum);
        cell = (row != null) ? row.getCell(cellnum) : null;

        DataFormatter formatter = new DataFormatter();
        String data = (cell != null) ? formatter.formatCellValue(cell) : "";

        wb.close();
        fi.close();
        return data;
    }

    // ✅ Write data into cell
    public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rownum);
        if (row == null)
            row = ws.createRow(rownum);

        cell = row.getCell(cellnum);
        if (cell == null)
            cell = row.createCell(cellnum);

        cell.setCellValue(data);

        fi.close();

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fo.close();
    }

    // ✅ Fill GREEN color
    public void fillGreenColor(String sheetName, int rownum, int cellnum) throws IOException {

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rownum);
        cell = row.getCell(cellnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fi.close();

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fo.close();
    }

    // ✅ Fill RED color
    public void fillRedColor(String sheetName, int rownum, int cellnum) throws IOException {

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rownum);
        cell = row.getCell(cellnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fi.close();

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fo.close();
    }
}
