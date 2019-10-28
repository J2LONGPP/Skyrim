package com.demo.spring.mapper;

import com.demo.spring.service.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author long.yu
 * @date 2019-04-01
 * @desc PDF导出视图类，实现PdfExportService
 */
public class PdfViewMapper extends AbstractPdfView {
     //导出服务接口
    private PdfExportService pdfExportService=null;

    public PdfViewMapper(PdfExportService pdfExportService) {
        this.pdfExportService=pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用导出服务类
        pdfExportService.make(model,document,writer,request,response);
    }
}
