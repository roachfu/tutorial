package com.roachfu.tutorial.excel;

import com.roachfu.tutorial.address.split.Address;
import com.roachfu.tutorial.address.split.ChineseAddressParser;
import com.roachfu.tutorial.similarity.SimilarTutorial;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author roach
 * @date 2018/8/22
 */

@Slf4j
public class ImageExcelUtils {

    private static Store[] optimalStores;

    private static void initOptimalStores() {
        if (optimalStores == null) {
            optimalStores = new Store[5];
            Store s = Store.builder().ratio(0).build();
            optimalStores[0] = s;
            optimalStores[1] = s;
            optimalStores[2] = s;
            optimalStores[3] = s;
            optimalStores[4] = s;
        }
    }

    public static void setOptimalStores(Store store) {
        initOptimalStores();

        if (optimalStores[0].getRatio() > store.getRatio()) {
            return;
        }
        optimalStores[0] = store;

        Arrays.sort(optimalStores);
    }

    public static void parseGoDeep() {
        List<Store> stores = parseSfa();

        // 创建文件对象, 同时支持Excel 2003、2007
        File excelFile = new File("C:\\Users\\1\\Desktop\\美赞臣项目\\附件B：Go Deep门店清单.xlsx");

        String path = "C:\\Users\\1\\Desktop\\美赞臣项目\\1.xlsx";

        try (FileInputStream in = new FileInputStream(excelFile)) {

            ExcelUtils.checkExcelValid(excelFile);
            Workbook workbook = ExcelUtils.getWorkbook(in, excelFile);

            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            System.out.println("一共有" + sheet.getLastRowNum() + "行");

            for (int i = 5; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                //如果当前行没有数据，跳出循环
                if (row.getCell(1) == null) {
                    continue;
                }

                GoDeep goDeep = GoDeep.builder()
                        .store(ExcelUtils.getValue(row.getCell(1)).toString())
                        .address(ExcelUtils.getValue(row.getCell(28)).toString())
                        .build();

                // 详细地址解析成省市区
                Address address = ChineseAddressParser.assembleAddress(goDeep.getAddress());
                goDeep.setCity(address.getCity());
                log.info(goDeep.toString());

                // 设置省
                Cell cell = row.getCell(18);
                cell.setCellValue(address.getProvince());
                // 设置市
                cell = row.getCell(19);
                cell.setCellValue(address.getCity());
                // 设置区县
                cell = row.getCell(21);
                cell.setCellValue(address.getCounty());
                // 设置街道
                cell = row.getCell(22);
                cell.setCellValue(address.getStreet());
                // 其他
                cell = row.getCell(23);
                cell.setCellValue(address.getOther());


                optimalStores = null;
                for (Store store : stores) {

                    // 市级相似度
                    double cityRatio = SimilarTutorial.calcSimilarity(store.getCity(), goDeep.getCity());
                    // 门店名相似度
                    double nameRatio = SimilarTutorial.calcSimilarity(store.getName(), goDeep.getStore());
                    // 详细地址相似度
                    double addressRatio = SimilarTutorial.calcSimilarity(store.getAddress(), goDeep.getAddress());
                    double ratio = cityRatio * 0.2 + nameRatio * 0.3 + addressRatio * 0.5;
                    store.setRatio(SimilarTutorial.halfUpDouble(ratio));

                    setOptimalStores(store);
                }

                int ratioCell = 29;
                for (int k = optimalStores.length - 1; k >= 0; k--) {
                    Store store = optimalStores[k];

                    cell = row.getCell(ratioCell++);
                    cell.setCellValue(store.getNumber());

                    cell = row.getCell(ratioCell++);
                    cell.setCellValue(store.getName());

                    cell = row.getCell(ratioCell++);
                    cell.setCellValue(store.getAddress());

                    cell = row.getCell(ratioCell++);
                    cell.setCellValue(store.getRatio());
                }
            }
            log.info("开始生成文件了。。。");
            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(path);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            //清空缓冲区数据
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            log.error("异常：", e);
        }
    }

    public static List<GoDeep> getGoDeepList() {
        List<GoDeep> goDeepList = new ArrayList<>();

        // 创建文件对象, 同时支持Excel 2003、2007
        File excelFile = new File("C:\\Users\\1\\Desktop\\美赞臣项目\\附件B：Go Deep门店清单.xlsx");

        try (FileInputStream in = new FileInputStream(excelFile)) {

            ExcelUtils.checkExcelValid(excelFile);
            Workbook workbook = ExcelUtils.getWorkbook(in, excelFile);

            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            log.info("一共有" + sheet.getLastRowNum() + "行");

            for (int i = 5; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                //如果当前行没有数据，跳出循环
                if (row.getCell(1) == null) {
                    continue;
                }

                GoDeep goDeep = GoDeep.builder()
                        .store(ExcelUtils.getValue(row.getCell(1)).toString())
                        .address(ExcelUtils.getValue(row.getCell(28)).toString())
                        .build();

                Address address = ChineseAddressParser.assembleAddress(goDeep.getAddress());
                goDeep.setCity(address.getCity());
//                log.info(goDeep.toString());

                goDeepList.add(goDeep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goDeepList;
    }

    public static List<Store> parseSfa() {
        List<Store> storeList = new ArrayList<>();

        // 创建文件对象, 同时支持Excel 2003、2007
        File excelFile = new File("C:\\Users\\1\\Desktop\\美赞臣项目\\附件A：SFA outlet list20180821.xlsx");

        try (FileInputStream in = new FileInputStream(excelFile)) {

            ExcelUtils.checkExcelValid(excelFile);
            Workbook workbook = ExcelUtils.getWorkbook(in, excelFile);

            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            log.info("一共有" + sheet.getLastRowNum() + "行");

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(0) == null) {
                    return storeList;
                }

                // 门店所在市
                String storeCity = ExcelUtils.getValue(row.getCell(3)).toString();
                // 门店编号
                String storeNum = ExcelUtils.getValue(row.getCell(7)).toString();
                // 门店名称
                String storeName = ExcelUtils.getValue(row.getCell(8)).toString();
                // 门店地址
                String storeAddress = ExcelUtils.getValue(row.getCell(14)).toString();

                Store store = Store.builder()
                        .city(storeCity)
                        .number(storeNum)
                        .name(storeName)
                        .address(storeAddress)
                        .build();

//                log.info(store.toString());

                storeList.add(store);
            }
        } catch (Exception e) {
            log.error("解析sfa异常：", e);
        }
        return storeList;
    }

    public static void main(String[] args) {
        /*
        List<GoDeep> goDeepList = getGoDeepList();

        List<Store> stores = parseSfa();

        for (GoDeep goDeep : goDeepList) {
            optimalStores = null;
            for (Store store : stores) {

                // 市级相似度
                double cityRatio = SimilarTutorial.calcSimilarity(store.getCity(), goDeep.getCity());
                // 门店名相似度
                double nameRatio = SimilarTutorial.calcSimilarity(store.getName(), goDeep.getStore());
                // 详细地址相似度
                double addressRatio = SimilarTutorial.calcSimilarity(store.getAddress(), goDeep.getAddress());

                double ratio = cityRatio * 0.4 + nameRatio * 0.3 + addressRatio * 0.3;
                store.setRatio(ratio);
                setOptimalStores(store);
            }
            for (Store store : optimalStores) {
                log.info(store.toString());
            }
        }*/

        parseGoDeep();
//        parseSfa();
    }
}
