package util;//import java.awt.image.BufferedImage;





import org.junit.Test;
import pdftopicture.PdfUtil;
import wordtopicture.WordUtil;

import java.io.IOException;
import java.io.InputStream;


public class TestFilter {


    @Test
    public void ReadWordFile() {
        InputStream inputStream = null;
        try {
                //查看pdf转单页
                //AsposeUtil.getPDFBinary("/Users/cuixiaoyan/Downloads/072514421986_0测试三元素周期表测试0721测评.pdf");

            //pdf多页
            String pdfBinary = WordUtil.parseFileToBase64_PNG1("/Users/cuixiaoyan/Downloads/几何测试(学生版).docx");

            System.out.println(pdfBinary);

            //压缩测试
//            File f = new File("/Users/cuixiaoyan/Downloads/浏览器下载");
//            new CompactAlgorithm(new File( "/Users/cuixiaoyan/Downloads/",f.getName()+".zip")).zipFiles(f);



//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
