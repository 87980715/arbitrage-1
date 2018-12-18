package com.shiyou.arbitrage.common;

import com.shiyou.arbitrage.data.model.Symbol;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @Package: com.shiyou.arbitrage.common
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/18 0018 16:24
 */
public class ExcelUtil {

    public static void buildExcel(List<Symbol> list){


        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("交易对表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式


        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("交易平台");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("交易对名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("价格小数");
        cell.setCellStyle(style);
        cell = row.createCell((short)4);
        cell.setCellValue("数量小数");
        cell.setCellStyle(style);


        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            Symbol symbol = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(symbol.getId().toString());
            row.createCell((short) 1).setCellValue(symbol.getPlatform());
            row.createCell((short) 2).setCellValue(symbol.getName());
            row.createCell((short) 3).setCellValue(symbol.getPriceDecimal());
            row.createCell((short) 4).setCellValue(symbol.getAmountDecimal());
        }
        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("E:/symbol.xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
