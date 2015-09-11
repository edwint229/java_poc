package org.apache.poi.xls;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

public class XlsBuilder<T> implements IExcelBuilder<T> {

    private Workbook workbook;

    private Sheet sheet;

    private CellStyle headerCellStyle;

    private CellStyle columnCellStyle;

    private CellStyle normalCellStyle;

    private CreationHelper createHelper;

    private String directory;

    private String fileName;

    private int rowIndex = 0;

    private String title;

    private Map<String, String> columnMap;

    private List<T> rowDatas;

    public XlsBuilder() {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
        createHelper = workbook.getCreationHelper();
    }

    protected CellStyle defaultHeaderStyle() {
        if (null == headerCellStyle) {
            headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(this.getFont("Calibri", 24));
            headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerCellStyle.setBorderBottom((short) 1);
            headerCellStyle.setBorderLeft((short) 1);
            headerCellStyle.setBorderRight((short) 1);
            headerCellStyle.setBorderTop((short) 1);
        }

        return headerCellStyle;
    }

    protected CellStyle defaultColumnStyle() {
        if (null == columnCellStyle) {
            columnCellStyle = workbook.createCellStyle();
            columnCellStyle.setFont(this.getFont("Calibri", 13));
            columnCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            columnCellStyle.setBorderBottom((short) 1);
            columnCellStyle.setBorderLeft((short) 1);
            columnCellStyle.setBorderRight((short) 1);
            columnCellStyle.setBorderTop((short) 1);
            columnCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            columnCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }

        return columnCellStyle;
    }

    protected CellStyle defaultStyle() {
        if (null == normalCellStyle) {
            normalCellStyle = workbook.createCellStyle();
            normalCellStyle.setFont(this.getFont("Calibri", 12));
            normalCellStyle.setBorderBottom((short) 1);
            normalCellStyle.setBorderLeft((short) 1);
            normalCellStyle.setBorderRight((short) 1);
            normalCellStyle.setBorderTop((short) 1);
        }

        return normalCellStyle;
    }

    private Font getFont(String fontName, int fontSize) {
        Font f = workbook.createFont();
        f.setFontName(fontName);
        f.setFontHeightInPoints((short) fontSize);
        return f;
    }

    @Override
    public void addTitle(String title) {
        this.title = title;
    }

    @Override
    public void addColumn(Map<String, String> columnMap) {
        this.columnMap = columnMap;
    }

    @Override
    public void addRowData(List<T> rowDatas) {
        this.rowDatas = rowDatas;
    }

    @Override
    public void build() throws Exception {
        this.fillTitle();
        this.fillColumnNames();
        this.fillBody();
        this.setColumnSize();

        this.generateXlsFile();
    }

    private void setColumnSize() {
        for (int i = 0; i < columnMap.size(); i++) {
            this.sheet.autoSizeColumn(i);
            // this.sheet.setColumnWidth(0, 5000);
        }
    }

    private void generateXlsFile() throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getFileOutputPath());
            workbook.write(fos);
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(workbook);
        }
    }

    private void fillTitle() {
        if (StringUtils.isBlank(title)) {
            return;
        }

        Row tmpRow = sheet.createRow(rowIndex++);
        Cell tmpCell = tmpRow.createCell(0);
        tmpCell.setCellValue(createHelper.createRichTextString(title));
        tmpCell.setCellStyle(this.defaultHeaderStyle());

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnMap.size() - 1));
    }

    private void fillColumnNames() {
        if (MapUtils.isEmpty(columnMap)) {
            return;
        }

        Row tmpRow = sheet.createRow(rowIndex++);
        Collection<String> values = columnMap.values();
        int cellnum = 0;

        for (String columnName : values) {
            Cell tmpCell = tmpRow.createCell(cellnum++);
            tmpCell.setCellValue(createHelper.createRichTextString(columnName));
            tmpCell.setCellStyle(this.defaultColumnStyle());
        }
    }

    private void fillBody() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (null == rowDatas) {
            return;
        }

        for (T rowData : rowDatas) {
            Row tmpRow = sheet.createRow(rowIndex++);
            int cellnum = 0;
            Map<String, String> describe = BeanUtils.describe(rowData);

            if (MapUtils.isEmpty(columnMap)) {
                Iterator<Entry<String, String>> iterator = describe.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> next = iterator.next();

                    Cell tmpCell = tmpRow.createCell(cellnum++);
                    tmpCell.setCellValue(createHelper.createRichTextString(next.getValue()));
                    tmpCell.setCellStyle(this.defaultStyle());
                }
            } else {
                Set<String> keySet = columnMap.keySet();
                for (String key : keySet) {
                    Cell tmpCell = tmpRow.createCell(cellnum++);
                    tmpCell.setCellValue(createHelper.createRichTextString(describe.get(key)));
                    tmpCell.setCellStyle(this.defaultStyle());
                }
            }
        }
    }

    private String getFileOutputPath() {
        return new StringBuffer(this.directory).append(File.separator).append(this.fileName).toString();
    }

    @Override
    public void addPath(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
