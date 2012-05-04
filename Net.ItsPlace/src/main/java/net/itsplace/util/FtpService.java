package net.itsplace.util;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class FtpService {
	private final String FTPURL = "211.229.107.60";
	private final int FTPPORT=21;
	private final String FTPUSER ="itsplace";
	private final String FTPPASS = "itsplace";
	private static final Logger logger = LoggerFactory.getLogger(FtpService.class);
	
	public boolean sendUpLoad(String year, String month,String fileName, ByteArrayInputStream bis) throws Exception {
		boolean isSuccess = false;
		FTPClient ftp = null;
		//BufferedInputStream bis = null;
		int reply;
		try{
			ftp = new FTPClient();
			ftp.connect(FTPURL, FTPPORT);
			reply = ftp.getReplyCode();
			
			
			if(!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				logger.info("FTP server refused connection");
			}
			
			if(!ftp.login(FTPUSER, FTPPASS))
			{
				ftp.logout();
				throw new Exception("FTP Login Failed");
			}
			
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			
			
			
		
	//		bis = new BufferedInputStream(new FileInputStream(file));
			//bis = new BufferedInputStream(commonFile.getInputStream());
			
			if(ftp.makeDirectory("/www/img/"+year+"/")){
				logger.info(year+"년 디렉토리 생성");
			}
			if(ftp.makeDirectory("/www/img/"+year+"/"+month+"/")){
				logger.info(month+"월 디렉토리 생성");
			}
			String temp = "/www/img/"+year+"/"+month+"/" + fileName;
			isSuccess = ftp.storeFile(temp, bis);
			logger.info(temp);
			if(isSuccess == false)
			{
				logger.info("FTP 파일쓰기 실패"+fileName);
				throw new Exception("FTP server " + fileName + " save file failed");
			}else{
				logger.info(fileName+"FTP 파일쓰기성공");
			}
			ftp.logout();
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(bis != null)
			{
				try{bis.close();}catch(Exception e){}
			}
			if(ftp!= null && ftp.isConnected())
			{
				try{ftp.disconnect();}catch(Exception e){}
			}
		}
		
		return isSuccess;
	}

}
