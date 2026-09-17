package com.nttdata.idmccnobe.view;

import com.nttdata.idmccnobe.util.CommonUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

/**
 *
 * @author DelorenziVa
 */
public class ExcelView extends AbstractXlsxStreamingView{
    
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List data = (List)model.get("EXCEL_DATA_KEY");
        String filename = (String)model.get("EXCEL_FILENAME_KEY");
        
        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".xlsx\"");
        
        writeToExcel(data, workbook, filename);
    }
    
    private <T> void writeToExcel(List<T> data, Workbook workbook, String sheetName) {
        try {
            Sheet sheet = workbook.createSheet(sheetName);
            sheet.setDefaultColumnWidth(18);
            if (data != null && !data.isEmpty()) {
                List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
                
                fieldNames.removeIf(x -> x.equals("serialVersionUID"));
                
                int rowCount = 0;
                int columnCount = 0;
                Row row = sheet.createRow(rowCount++);
                
                // create style for header cells
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setFontName("Arial");
                style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font.setBold(true);
                font.setColor(IndexedColors.WHITE.getIndex());
                style.setFont(font);
                
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount++);
                    cell.setCellValue(fieldName);
                    cell.setCellStyle(style);
                }
                Class<? extends Object> classz = data.get(0).getClass();
                for (T t : data) {
                    row = sheet.createRow(rowCount++);
                    columnCount = 0;
                    for (String fieldName : fieldNames) {
                        try {
                            Cell cell = row.createCell(columnCount);
                            Method method = null;
                            try {
                                method = classz.getMethod("get" + CommonUtils.capitalize(fieldName));
                            } catch (NoSuchMethodException nme) {
                                method = classz.getMethod("get" + CommonUtils.capitalize(fieldName));
                            }
                            Object value = method.invoke(t, (Object[]) null);
                            if (value != null) {
                                if (value instanceof String) {
                                    cell.setCellValue((String) value);
                                    if (((String) value).toLowerCase().startsWith("http")) {
                                        Hyperlink href = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                                        href.setAddress((String) value);
                                        cell.setHyperlink(href);
                                    }
                                } else if (value instanceof Long) {
                                    cell.setCellValue((Long) value);
                                } else if (value instanceof Integer) {
                                    cell.setCellValue((Integer) value);
                                } else if (value instanceof Double) {
                                    cell.setCellValue((Double) value);
                                }
                            }
                            
                        }  catch (Exception ex) {
                            logger.error(ex.getMessage(), ex);
                        }
                        columnCount++;
                    }
                }
                
                /* doesn't work in stream mode
                int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int i = 0; i < noOfColumns; i++) {
                sheet.autoSizeColumn(i);
                }
                */
                
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    //retrieve field names from a POJO class
    private List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
    
}
