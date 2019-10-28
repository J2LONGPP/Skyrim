package com.demo.spring.service;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author long.yu
 * @date 2019-04-01
 * @desc 因为AbstractPdfView是一个抽象类，在继承它后，就要实现其定义的抽象方法，从而完成导出的逻辑，而各个控制器都会有不同的导出逻辑。
 * 为了适应不同控制器的自定义导出，这里先定义导出的接口,这样各个控制器只需要实现这个接口，就能够自定义其导出PDF的逻辑，接着就是继承AbstractPdfView
 * 的非抽象类，通过调度PdfExportService的make的方法就可以让控制器实现自定义的导出逻辑
 */
public interface PdfExportService {
    public void make(Map<String,Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}
