package com.roachfu.tutorial.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author roach
 * @date 2018/8/22
 */
public class ExcelUtils {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream in, File file) throws IOException {
        Workbook wb = null;
        //Excel 2003
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
        }
        // Excel 2007/2010
        else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     *
     * @throws Exception
     */
    public static void checkExcelValid(File file) throws Exception {
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }

        boolean flag = file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX));
        if (!flag) {
            throw new IOException("文件不是Excel");
        }
    }

    /**
     * 创建excel单元格
     *
     * @param row       行对象
     * @param col       列号（从0开始）
     * @param cellValue 列值（字符串）
     * @param cellStyle 列样式（居中等样式）
     */
    public static void createCell(Row row, int col, String cellValue, CellStyle
            cellStyle) {
        Cell cell = row.createCell(col, CellType.STRING);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }

    public static void setCellValue(Row row, int col, String cellValue, CellStyle cellStyle){
        Cell cell = row.getCell(col);
        if (cell == null){
            ExcelUtils.createCell(row, col, cellValue, cellStyle);
        }else {
            cell.setCellValue(cellValue);
        }
    }

    /**
     * 居中单元格样式
     *
     * @param workbook workbook对象
     * @return CellStyle
     */
    public static CellStyle leftCenter(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 创建文件对象, 同时支持Excel 2003、2007
            File excelFile = new File("C:\\Users\\1\\Desktop\\美赞臣项目\\附件B：Go Deep门店清单.xlsx");
            // 文件流
            FileInputStream in = new FileInputStream(excelFile);
            checkExcelValid(excelFile);
            Workbook workbook = getWorkbook(in, excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

            // Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            System.out.println("一共有" + sheetCount + "个sheet");

            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获取总行数
            System.out.println("一共有" + sheet.getLastRowNum() + "行");

            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                try {
                    // 跳过第一和第二行的目录
                    if (count < 2) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if (row.getCell(1) == null) {
                        return;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    System.out.println("总列数：" + columnTotalNum);

                    System.out.println("最大列数：" + row.getLastCellNum());

                    //for循环的，不扫描空格的列
//                    for (Cell cell : row) {
//                    	System.out.println(cell);
//                    }
                    int end = row.getLastCellNum();

                    Cell cell = row.getCell(1);
                    System.out.print(getValue(cell) + "\t");

                    cell = row.getCell(28);
                    System.out.println(getValue(cell));

                    /*for (int i = 0; i < end; i++) {
                        Cell cell = row.getCell(i);
                        if (cell == null) {
                            System.out.print("null" + "\t");
                            continue;
                        }

                        Object obj = getValue(cell);
                        System.out.print(obj +  "\t");
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    obj = cell.getDateCellValue();
                } else {
                    obj = cell.getNumericCellValue();
                    if (obj.toString().endsWith(".0")) {
                        obj = ((Double) obj).intValue();
                    } else if (obj.toString().contains("E")) {
                        //科学计数法问题处理
                        DecimalFormat df = new DecimalFormat("0");
                        obj = df.format(obj);
                    }
                }
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }
}

