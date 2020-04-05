package com.web.common;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToPng {

    public static void main(String[] args) {
        File file = new File("D:\\log\\my score.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                // 方式1,第二个参数是设置缩放比(即像素)
//                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                BufferedImage image = renderer.renderImageWithDPI(i, 169);
                // 方式2,第二个参数是设置缩放比(即像素)
                // BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, "PNG", new File("D:\\log\\my score" + i + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
