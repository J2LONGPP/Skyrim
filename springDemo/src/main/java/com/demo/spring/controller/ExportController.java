package com.demo.spring.controller;

import com.demo.spring.entity.Student;

import com.demo.spring.mapper.PdfViewMapper;
import com.demo.spring.service.PdfExportService;
import com.demo.spring.service.TestService;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;

/**
 * @author long.yu
 * @date 2019-04-02
 * @desc 导出PDF数据
 * @version 0.0.1
 */
@RestController
@RequestMapping("/pdf")
public class ExportController {
    @Autowired
    private TestService testService=null;

    @GetMapping("/test")
    public ModelAndView exportPdf(){
        //查询用户信息
        Student stu=this.testService.getName();
        //定义PDF视图
        View view=new PdfViewMapper(exportService());
        //设置视图
        ModelAndView mv=new ModelAndView();
        mv.setView(view);
        //加入数据模型
        mv.addObject("stu",stu);
        return mv;
    }

    //导出PDF自定义
    private PdfExportService exportService(){
       //使用Lambda表达式自定义导出
        return ((model, document, writer, request, response) -> {
            try{
                //A4纸张
                document.setPageSize(PageSize.A4);
                //标题
                document.addTitle("用户信息");
                //换行
                document.add(new Chunk("\n"));
                //表格 3列
                PdfPTable table=new PdfPTable(3);
                //单元格
                PdfPCell cell=null;
                //字体 蓝色加粗
                Font f8=new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                //标题
                cell=new PdfPCell(new Paragraph("user_name",f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格
                table.addCell(cell);
                cell=new PdfPCell(new Paragraph("note",f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格
                table.addCell(cell);
                //获取数据模型中的用户列表
                Student stu=(Student) model.get("stu");
                //添加数据
                //换行
                document.add(new Chunk("\n"));
                cell=new PdfPCell(new Paragraph(stu.getId()));
                table.addCell(cell);
                cell=new PdfPCell(new Paragraph(stu.getName()));
                table.addCell(cell);
                //在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }
}
