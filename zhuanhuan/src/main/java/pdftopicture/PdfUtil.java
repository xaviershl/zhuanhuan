package pdftopicture;

import util.CompactAlgorithm;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import util.MergeImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *pdf 转图片
 */

public class PdfUtil {



	/**
	 * 将pdf转成一张图片
	 * @param pdffile
	 * @return
	 * @throws IOException
	 */
	public static String getPDFBinary(String pdffile) throws IOException {
		//文件流
		InputStream inputStream = new FileInputStream(pdffile);
		//文件 获取文件名字
		File file = new File(pdffile);
		String name = file.getName();
		//截取不带后缀名的字段
		String fileName = name.substring(0, name.lastIndexOf("."));

		//文件上传路径
		String parent = file.getParent();

		PDDocument doc = PDDocument.load(inputStream);
		//获取pdf文件 页数
		List<PDPage> pages = doc.getDocumentCatalog().getAllPages();

		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		BufferedImage image = null;
		for (int i = 0; i < pages.size(); i++) {
			PDPage page = (PDPage)pages.get(i); // 获取第i页
			image = page.convertToImage();
			imageList.add(image);
			//合并一张
			image = MergeImage.mergeImage(false, imageList);
		}

		boolean png = ImageIO.write(image, "png", new File(parent +"/"+ fileName + ".png"));
		if(png == false){
			return "转换失败";
		}

		//关闭流
		inputStream.close();
		return "转换成功";


	}


	// 将pdf 转化为 图片分页 压缩包
 	public static String getPDFBinarys(String pdffile) throws IOException {
		//文件流
		InputStream inputStream = new FileInputStream(pdffile);
		PDDocument doc = PDDocument.load(inputStream);

		//文件 获取文件名字
		File file = new File(pdffile);
		String name = file.getName();
		//截取不带后缀名的字段
		String fileName = name.substring(0, name.lastIndexOf("."));
		//文件上传路径
		String parent = file.getParent();

		//获取pdf文件 页数
		List<PDPage> pages = doc.getDocumentCatalog().getAllPages();

		BufferedImage image = null;

		//创建同名文件夹
		new File(parent+"/"+ fileName).mkdir();

		for (int i = 0; i < pages.size(); i++) {
			PDPage page = (PDPage) pages.get(i); // 获取第i页
			image = page.convertToImage();
			ImageIO.write(image, "png", new File(parent +"/"+ fileName +"/"+ "第"+ i +"页" + fileName + ".png"));

		}

		//压缩同名文件夹
		    File f = new File(parent  +"/" + fileName );
            new CompactAlgorithm(new File( parent+ "/",f.getName()+".zip")).zipFiles(f);

		//关闭流
		inputStream.close();


		return "转换成功"+pages.size()+"页";

	}






}
