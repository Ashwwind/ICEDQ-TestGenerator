package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    /**
     * Cache: key = "filePath::sheetName" → value = parsed data.
     * <p>
     * Without caching, each @DataProvider call opens the same Excel file on disk.
     * With caching, the file is read once per JVM lifetime.
     * <p>
     * ConcurrentHashMap is safe for parallel DataProvider execution.
     */
    private static final Map<String, Object[][]> cache = new ConcurrentHashMap<>();

    /**
     * Reads all data rows from the given sheet, with in-memory caching.
     *
     * @param filePath  path to the .xlsx file
     * @param sheetName exact name of the sheet tab
     * @return 2-D array: rows × columns (header row excluded)
     */
    public static Object[][] readExcel(String filePath, String sheetName) {
        String cacheKey = STR."\{filePath}::\{sheetName}";
        return cache.computeIfAbsent(cacheKey, _ -> loadExcel(filePath, sheetName));
    }

    // ── Private loader — only called when the entry is not in cache ─────────────────────

    private static Object[][] loadExcel(String filePath, String sheetName) {
        // FIX: try-with-resources closes FileInputStream AND Workbook in all cases.
        // Original code leaked the stream if XSSFWorkbook() threw an exception.
        // DataFormatter handles numeric/date cells correctly (cell.toString() gives "1.0" for int 1).
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException(STR."Sheet '\{sheetName}' not found in: \{filePath}");
            }

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            DataFormatter formatter = new DataFormatter();

            Object[][] data = new Object[rows - 1][cols];
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = (row != null) ? row.getCell(j) : null;
                    // formatter.formatCellValue handles numeric, date, formula, blank cells
                    data[i - 1][j] = (cell != null) ? formatter.formatCellValue(cell) : "";
                }
            }
            return data;

        } catch (IOException e) {
            throw new RuntimeException(STR."Excel read failed [\{filePath} / \{sheetName}]: \{e.getMessage()}", e);
        }
    }
}
