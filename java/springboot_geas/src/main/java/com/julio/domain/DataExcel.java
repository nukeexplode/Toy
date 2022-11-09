package com.julio.domain;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julio
 * @date 2020/2/15-19:23
 * @Description 通用的可供下载数据的excel表格实体类
 **/
public class DataExcel {
    //文件名
    private String fileName;
    //列名数组
    private String[] rowName;
    //数据数组
    private List<Object[]> dataList=new ArrayList<>();//注意这里是一个数组集合，即传进来的值是可能个二维的！
    //用于下载的response
    private HttpServletResponse response;

    //构造方法，传入要导出的数据
    public DataExcel() {
    }

    public DataExcel(String fileName, String[] rowName, List<Object[]> dataList, HttpServletResponse response) {
        this.fileName = fileName;
        this.rowName = rowName;
        this.dataList = dataList;
        this.response = response;
    }

    //下载excel的方法
    public void downloadExcel() {
        HSSFWorkbook workbook=new HSSFWorkbook();//创建ecxel工作薄对象
        HSSFSheet sheet=workbook.createSheet("SSR");//创建excel表

        //产生行
        HSSFRow row=sheet.createRow(0);
        row.createCell(0);

        //sheet样式定义
        HSSFCellStyle columnTopStyle=this.getColumnTopStyle(workbook);//获取列头样式对象
        HSSFCellStyle style=this.getStyle(workbook);//单元格样式对象

        //定义所需列数
        int columnNum=rowName.length;
        HSSFRow rowRowName=sheet.createRow(0);//在索引1的位置创建行

        //将列头设置到sheet的单元格中
        for(int n=0;n<columnNum;n++){
            HSSFCell cellRowName=rowRowName.createCell(n);//创建列头对应个数的单元格
            cellRowName.setCellType(CellType.STRING);
            HSSFRichTextString text=new HSSFRichTextString(rowName[n]);//格式化列头
            cellRowName.setCellValue(text);//设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);//设置列头单元格样式
        }

        //将查询到的数据设置到sheet对应的单元格中
        for(int i=0;i<dataList.size();i++){
            Object[] obj = dataList.get(i);//遍历集合中的每个数组对象
            HSSFRow dataRow=sheet.createRow(1+i);//在索引2的位置创建一行,如果是多行数据需要加i
            for(int j=0;j<obj.length;j++){
                HSSFCell cellData=null;
                cellData = dataRow.createCell(j);//创建对应个数的单元格
                //cellData.setCellType(CellType.STRING);//设置单元格数据类型
                if (obj[j]!=null){
                    cellData.setCellValue(obj[j].toString());//设置数据单元格的值
                }
                cellData.setCellStyle(style);
            }
        }

        //自适应列宽
        for(int i=0 ;i<rowName.length;i++){
            sheet.autoSizeColumn((short)i);
        }

        if(workbook!=null){
            try{
                if(response!=null){
                    //response.setContentType("application/x-download;charset=utf-8");
                    response.setContentType("application/vnd.ms-excel;charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                    OutputStream out = response.getOutputStream();
                    workbook.write(out);
                    out.close();
                }else{
                    FileOutputStream outputStream=new FileOutputStream("C:/"+fileName);//如果文件不存在则创建一个文件
                    workbook.write(outputStream);
                    outputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //设置列头单元格样式
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook){
        //设置字体
        HSSFFont font=workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //设置字体名称
        font.setFontName("Arial");
        //字体加粗
        font.setBold(true);
        //设置单元格样式
        HSSFCellStyle style=workbook.createCellStyle();
        //设置上下左右边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);

        //设置边框颜色
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());

        //样式中应用设置的字体
        style.setFont(font);
        return style;
    }

    //设置数据单元格样式
    public HSSFCellStyle getStyle(HSSFWorkbook workbook){
//设置字体
        HSSFFont font=workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //设置字体名称
        font.setFontName("Arial");
        //字体不加粗
        font.setBold(false);
        //设置单元格样式
        HSSFCellStyle style=workbook.createCellStyle();
        //设置上下左右边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);

        //设置边框颜色
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());

        //样式中应用设置的字体
        style.setFont(font);
        return style;
    }
}
