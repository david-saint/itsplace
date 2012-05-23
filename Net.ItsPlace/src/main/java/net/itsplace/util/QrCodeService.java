package net.itsplace.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.itsplace.domain.Place;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCodeService {
	private static final Logger logger = LoggerFactory
			.getLogger(QrCodeService.class);
	/**
	 * 가맹점 대표 QRcode를 fid로 생성한다 <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param place 검색
	 * @param url 
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	public Place makePlaceQrCode(Place place, String url) {
		try {

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			logger.info("qrcode 생성:{}",Integer.toString(place.getFid()));
			String qrText = "http://www.itsplace.net/place/view/" + place.getFid();
			qrText = new String(qrText.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix bitMatrix = qrCodeWriter.encode(qrText,BarcodeFormat.QR_CODE, 100, 100);

			//MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(srcName));
			byte[] imageInByte = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
			String sysYear = DateUtil.getSysYear();
 			String sysMonth = DateUtil.getSysMonth();
 			String fileName = "qr"+place.getFid()+".png";

 			String newFileName = "/"+sysYear+"/"+sysMonth+"/"+fileName;
			logger.info("타켓DIR: " + newFileName);
			
			place.setQrcode(newFileName);	 
			
			FtpService ftp = new FtpService();
			ftp.sendUpLoad(sysYear,sysMonth,fileName,bis);
		

		
		} catch (Exception e) {
			place = null;
			logger.info(e.getMessage());
		} 
		return place;
	}
	
	/**
	 * 가맹점  QR인증코드를   생성한다  앱에서 인식후 임의의 인즈언호입력창을 호출하고 인증번호를 입력하면 적립한다. <br />
	 * 
	 * @author 김동훈
	 * @version 1.0, 2011. 8. 24.
	 * @param place 검색
	 * @param url 
	 * @return DataTables
	 * @throws 
	 * @see 
	 */
	public Place makePlaceQrAuthCode(Place place) {
		try {

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			logger.info("qrauthcode 생성:{}",Integer.toString(place.getFid()));
			String qrText =Integer.toString(place.getFid());
			qrText = new String(qrText.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix bitMatrix = qrCodeWriter.encode(qrText,BarcodeFormat.QR_CODE, 100, 100);

			//MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(srcName));
			byte[] imageInByte = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
			String sysYear = DateUtil.getSysYear();
 			String sysMonth = DateUtil.getSysMonth();
 			String fileName = "qr_authcode"+place.getFid()+".png";

 			String newFileName = "/"+sysYear+"/"+sysMonth+"/"+fileName;
			logger.info("타켓DIR: " + newFileName);
			
			place.setQrAuthCode(newFileName);	 
			
			FtpService ftp = new FtpService();
			ftp.sendUpLoad(sysYear,sysMonth,fileName,bis);
		
		} catch (Exception e) {
			place = null;
			logger.info(e.getMessage());
		} 
		return place;
	}
}
