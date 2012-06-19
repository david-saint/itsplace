package net.itsplace.place.service;

import java.util.List;
import java.util.Map;

import net.itsplace.common.CommonService;
import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMedia;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.place.dao.PlaceInfoDao;
import net.itsplace.place.dao.PlaceMenuDao;
import net.itsplace.user.UserInfo;
import net.itsplace.util.ImageService;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("placeMenuService")
public class PlaceMenuServiceImpl implements PlaceMenuService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceMenuServiceImpl.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private PlaceMenuDao placeMenuDao;
	@Autowired
	private PagingManager pagingManaer;
	@Override
	public int saveMenu(PlaceMenu placeMenu) {
		return placeMenuDao.saveMenu(placeMenu);		
	}

	@Override
	public void editMenu(PlaceMenu placeMenu) {
		placeMenuDao.editMenu(placeMenu);		
	}

	@Override
	public void deleteMenu(int mnid) {
		placeMenuDao.deleteMenu(mnid);		
	}

	@Override
	public PlaceMenu getMenu(int mnid) {
		return placeMenuDao.getMenu(mnid);
	}

	@Override
	public DataTable<PlaceMenu> getMenuList(String[] columns, 
			Integer iDisplayStart,
			Integer iDisplayLength, 
			Integer iSortCol_0, 
			String sSortDir_0,
			String sSearch, 
			int fid) {
		    DataTable<PlaceMenu> table = iDisplayLength != null ?
                  new DataTable<PlaceMenu>(columns, sSortDir_0, iDisplayStart, iDisplayLength) :
                  new DataTable<PlaceMenu>(columns, sSortDir_0, iDisplayStart);
  
		
		  Map<String, Object> param  = pagingManaer.createDataTableLimit(iDisplayStart, iDisplayLength);
		  param.put("sortDirection", sSortDir_0);
		  param.put("sortColumn", table.getOrderColumn(iSortCol_0));
		  param.put("search", sSearch);
		  param.put("fid", fid);
			
		  List<PlaceMenu> placeMenuList = placeMenuDao.getMenuList(param);
		  logger.info("---------------->"+placeMenuList.get(0).getHost());
		  logger.info("---------------->"+placeMenuList.get(0).getHost());
		  logger.info("---------------->"+placeMenuList.get(0).getHost());
		  
		  pagingManaer.setTotalCount(pagingManaer.getFoundRows());
			
			
		 
		  table.setRows(placeMenuList); 
		  table.setiTotalDisplayRecords(pagingManaer.getTotalCount());
		  
		  return table;
	}
	@Override
	public PlaceMenu savePlaceMenuImage(ImageFileUpload file) {
		
		String orinalImagePath="";
		PlaceMenu placeMenu = new PlaceMenu();
		
		try {
			orinalImagePath = imageService.convertToPng(file.getFile(),280,230);
			placeMenu.setFilePath(orinalImagePath);
			placeMenu.setHost(commonService.getBasecd().getMediaImageHost());
			placeMenu.setmType(commonService.getBasecd().getMediaImage());
			placeMenu.setFid(file.getFid());
			if(file.getMnid() <= 0 ){
				int _mnid = saveMenu(placeMenu);				
				logger.info("mnid:{}",_mnid);
				placeMenu.setMnid(_mnid);
		
			}else{
				placeMenu.setMnid(file.getMnid());
				placeMenuDao.editPlaceMenuImage(placeMenu);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return placeMenu;
	}


}
