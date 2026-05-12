package com.shea.ai.sheaaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import static com.shea.ai.sheaaiagent.constant.FileConstant.FILE_SAVE_DIR;

/**
 * PDF生成工具类
 * @author : Shea.
 * @since : 2026/5/11 15:25
 */
public class PDFGenerationTool {

    @Tool(description = "Generate a PDF file with given content")
    public String generatePDF(
            @ToolParam(description = "Name of the PDF file to generate") String fileName,
            @ToolParam(description = "Content to be included in the PDF file") String content) {
        String fileDir = FILE_SAVE_DIR + "/pdf";
        String filePath = fileDir + "/" + fileName;
        try {
            FileUtil.mkdir(fileDir);
            try (
                    PdfWriter writer = new PdfWriter(filePath);
                    PdfDocument pdf =  new PdfDocument(writer);
                    Document document = new Document(pdf);
                    ) {
                // 设置中文字体
                PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
                document.setFont(font);
                // 添加内容
                Paragraph paragraph = new Paragraph(content);
                document.add(paragraph);
            }
            return "PDF generated successfully: " + filePath;
        }catch (Exception e) {
            return "Failed to generate PDF: " + e.getMessage();
        }
    }
}
