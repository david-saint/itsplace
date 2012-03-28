package com.myplace.partner.franchiser.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.myplace.util.DateUtil;

@Service("FranchiserImageService")
public class FranchiserImageServiceImpl  extends SqlMapClientDaoSupport implements FranchiserImageService{

	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@Inject 
	private FileSystemResource fsResource;
	
	@Override
	public  List<FranchiserImage>  getFranchiserImageList(FranchiserImage franchiserImage)
			throws DataAccessException {
	
		return getSqlMapClientTemplate().queryForList("getFranchiserImage",franchiserImage);
	}

	@Override
	public FranchiserImage saveFranchiserImage(FranchiserImage franchiserImage)
			throws DataAccessException {
		if(!franchiserImage.getFileData().isEmpty()){
			franchiserImage = convFranchiserImage(franchiserImage);
		}
		
		 getSqlMapClientTemplate().insert("saveFranchiserImage",franchiserImage);
		 
		 return franchiserImage;
	}
	


	public FranchiserImage convFranchiserImage(FranchiserImage franchiserImage){
		String filename = franchiserImage.getFileData().getOriginalFilename();
		franchiserImage.setFileName(filename);
		
		String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());

		
		if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF") || imgExt.equalsIgnoreCase("PNG")){
			byte[] bytes = franchiserImage.getFileData().getBytes();
			try{
				//logger.info(fsResource.getPath() + filename);
				String sysYear = DateUtil.getSysYear();
	 			String sysMonth = DateUtil.getSysMonth();
	 			String sysTime = DateUtil.getSysTime();
	 			String yearDir = fsResource.getPath()+ sysYear;
	 			String targerDir =yearDir +"/"+sysMonth+"/";
	 			
	 			File createYearDir = new File(fsResource.getPath(), sysYear);
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
 				logger.info("타켓DIR: " + targerDir+sysYear+sysMonth+sysTime); 
 				String srcName = targerDir+sysYear+sysMonth+sysTime+".png";
 				String fileName = sysYear+"/"+sysMonth+"/"+sysYear+sysMonth+sysTime+".png";
 				franchiserImage.setFileName(fileName);
				
 				File lOutFile = new File(srcName);
  			  
			    FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
			    lFileOutputStream.write(bytes);
			    lFileOutputStream.close();
			    
			    
			}catch(IOException ie){
				//Exception 처리
				logger.info("File writing error!" + ie.getMessage());
			}
			logger.info("franchiserMember File upload success! ");
		}else{
			logger.info("File type error! ");
		}
		return franchiserImage;
	}

}
