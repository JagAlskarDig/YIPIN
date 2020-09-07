package com.ypgly.yipin.utils.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.*;

/**
 * @author yzy
 */
public class FileUtils {

    private static final Logger log= LoggerFactory.getLogger(FileUtils.class);

    public static File multipartFileToFile(MultipartFile file){

        File toFile = null;
        try{
            if (file.equals("") || file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }
        }catch (Exception e){
            log.error("MultipartFile to File error"+e);
        }finally {
            //if()
        }

        return toFile;
    }

    //获取流文件
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readExcelToTxt( File file) {
        Workbook wb = null;
        String extString = file.getName().substring(file.getName().lastIndexOf("."));
        InputStream is = null;
        List<Map<String, String>> list = null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        String columns[]={"原文","译文"};
        try {
            is = new FileInputStream(file);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            if (wb != null) {
                // 用来存放表中数据
                list = new ArrayList<Map<String, String>>();
                // 获取第一个sheet
                sheet = wb.getSheetAt(0);
                // 获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                // 获取第二行
                row = sheet.getRow(1);
                // 获取最大列数
                //int colnum = row.getPhysicalNumberOfCells();
                int colnum = 2;
                for (int i = 1; i < rownum; i++) {
                    Map<String, String> map = new LinkedHashMap<String, String>();
                    row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < colnum; j++) {
                            cellData =(String) getCellFormatValue(row.getCell(j));
                            map.put(columns[j], cellData);
                        }
                    } else {
                        break;
                    }
                    list.add(map);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 遍历解析出来的list
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            int m=0;
            for (Map.Entry<String, String> entry : list.get(i).entrySet()) {
                String value = entry.getValue();
                if(m==0){
                    sb.append(value + "\t");
                    m++;
                }else{
                    sb.append(value);
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    // 读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }


    /**
     * 生成文件
     * @param str
     * @param filePath
     * @throws IOException
     */
    public static void WriteToFile(String str, String filePath) throws IOException {
        BufferedWriter bw = null;
        try {
            FileOutputStream out = new FileOutputStream(filePath, true);// true,表示:文件追加内容，不重新生成,默认为false
            bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
            bw.write(str += "\r\n");// 换行
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }
    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        // 数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

        public static void main(String[] args) throws IOException {
        FileUtils f=new FileUtils();
        // f.coverToWord(new File("C:\\Users\\iplus_alskar\\Desktop\\usingthymeleaf.pdf"));
    }
}
