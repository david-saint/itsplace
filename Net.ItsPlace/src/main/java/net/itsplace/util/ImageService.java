package net.itsplace.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ImageService {
	@Inject 
	private FileSystemResource fsResource;
	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
	/**
	 * 섬네일을 만든다 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param model
	 * @return  String 파일경로
	 * @throws Exception 
	 * @throws 
	 * @see 
	 */
	public String convertToPng(CommonsMultipartFile commonFile, int width, int height) throws Exception{
		String filename = commonFile.getOriginalFilename();
		String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
        String newFileName ="";
		
		if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF") || imgExt.equalsIgnoreCase("PNG")){
			byte[] bytes = commonFile.getBytes();
			try{
				
				bytes = resizeToPng(bytes,width,height);
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				String sysYear = DateUtil.getSysYear();
	 			String sysMonth = DateUtil.getSysMonth();
	 			String fileName = UUID.randomUUID().toString().replace("-","")+".png";
//	 			/ UUID.randomUUID().toString()

	 	/*		File createYearDir = new File(fsResource.getPath(), sysYear);
 				 try {
					FileUtils.forceMkdir(createYearDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
 				
 				 File createMonthDir = new File(yearDir+"/", sysMonth);
 				 try {
 					 FileUtils.forceMkdir(createMonthDir);
 				 } catch (IOException e) {
 					 e.printStackTrace();
 				 }
 				 */
	 			 newFileName = "/"+sysYear+"/"+sysMonth+"/"+fileName;
 				logger.info("타켓DIR: " + newFileName);
 				 
 			
 				
 			
				
 				//File lOutFile = new File(srcName);
  			  
			    /*FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
			    lFileOutputStream.write(bytes);
			    lFileOutputStream.close();
			    */
 				FtpService ftp = new FtpService();
 				ftp.sendUpLoad(sysYear,sysMonth,fileName,bis);
 				
 			
 				
			}catch(IOException ie){
				logger.info("File writing error!" + ie.getMessage());
			}
			logger.info("franchiserMember File upload success! ");
		}else{
			logger.info("File type error! ");
			newFileName="";
		}
		return newFileName;
	}

	private byte[] resizeToPng(byte[] imageBytes, int width, int height){
		BufferedImage bi = null;
		byte[] imageInByte = null;

		try {
			Image image = ImageIO.read(new ByteArrayInputStream(imageBytes));
	
	        if(width==0 && height==0){
	        	bi = ImageProcessor.scaling(image, image.getWidth(null), image.getHeight(null));
	        }else{
	        	bi = ImageProcessor.scaling(image, width, height);
	        }
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return imageInByte;

	}
	
}
