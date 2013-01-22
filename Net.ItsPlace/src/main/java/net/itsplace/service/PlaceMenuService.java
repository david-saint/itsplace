package net.itsplace.service;

import java.util.List;

import net.itsplace.domain.DataTable;
import net.itsplace.domain.ImageFileUpload;
import net.itsplace.domain.JpaPaging;
import net.itsplace.domain.Place;
import net.itsplace.domain.PlaceEvent;
import net.itsplace.domain.PlaceMenu;
import net.itsplace.repository.PlaceMenuRepository;
import net.itsplace.repository.PlaceRepository;
import net.itsplace.user.UserInfo;
import net.itsplace.util.ImageService;
import net.itsplace.util.PagingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
public interface PlaceMenuService {
	public PlaceMenu saveMenu(PlaceMenu placeMenu) ;
	public PlaceMenu savePlaceMenuImage(ImageFileUpload file) ;
	public PlaceMenu editMenu(PlaceMenu placeMenu) ;
	public void deleteMenu(int mnid)  ;
	public PlaceMenu getMenu(int mnid)  ;
	DataTable<PlaceMenu> getMenuList(JpaPaging paging, int fid);
	public List<PlaceMenu> findByPlace(int fid);
	
}
@Service("placeMenuService")
 class PlaceMenuServiceImpl implements PlaceMenuService {
	private static final Logger logger = LoggerFactory.getLogger(PlaceMenuService.class);
	@Autowired
	private BaseService commonService;
	@Autowired
	private ImageService imageService;
	@Autowired
	PlaceMenuRepository repo;
	@Autowired
	PlaceRepository placeRepo;
	@Autowired
	PlaceService placeService;
	@Override
	public PlaceMenu saveMenu(PlaceMenu placeMenu) {
		placeMenu.setIsDelete(false);

		PlaceMenu savedPlaceMenu = repo.save(placeMenu);
		return savedPlaceMenu;	
	}

	@Override
	public PlaceMenu editMenu(PlaceMenu placeMenu) {
		return repo.save(placeMenu);		
	}

	@Override
	public void deleteMenu(int mnid) {
		PlaceMenu placeMenu = repo.findOne(mnid);
		placeMenu.setIsDelete(true);
		repo.save(placeMenu);		
	}

	@Override
	public PlaceMenu getMenu(int mnid) {
		return  repo.findOne(mnid);
	}

	
	@Override
	public DataTable<PlaceMenu> getMenuList(JpaPaging paging, int fid) {
		  DataTable<PlaceMenu> table = new DataTable<PlaceMenu>(paging);
          //Page<BookInfo> books = (Page<BookInfo>)bookRepo.findByTitleContainingOrAuthors(paging.getsSearch(),paging.getsSearch(), paging.getPageable());
          
      
    		
    		
          Page<PlaceMenu> placeMenus = repo.findByPlace(placeRepo.findOne(fid), paging.getPageable());
         			 
          
		  table.setRows(placeMenus.getContent()); 
		  
		  table.setiTotalDisplayRecords(placeMenus.getTotalElements());
		  logger.info("결과:{}",table.getiDisplayLength());
		  logger.info("결과:{}",table.getiTotalRecords());
		  return table;
		  
	
	}
	@Override
	public PlaceMenu savePlaceMenuImage(ImageFileUpload file) {
		
		String orinalImagePath="";
		PlaceMenu placeMenu = repo.findOne(file.getFid());
		Place place = placeRepo.findOne(file.getFid());
		try {
			
			orinalImagePath = imageService.convertToPng(file.getFile(),280,230);
			placeMenu.setFilePath(orinalImagePath);
		//	placeMenu.setHost(commonService.getBasecd().getMediaImageHost());
		//	placeMenu.setmType(commonService.getBasecd().getMediaImage());
			placeMenu.setPlace(place);
			if(file.getId() <= 0 ){
				int _mnid = saveMenu(placeMenu).getMnid();				
				logger.info("savePlaceMenuImage mnid:{}",_mnid);
				placeMenu.setMnid(_mnid);
		
			}else{
				placeMenu.setMnid(file.getId());
				repo.save(placeMenu);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return placeMenu;
	}

	@Override
	public List<PlaceMenu> findByPlace(int fid) {
		return repo.findByPlace(placeService.getPlace(fid));
	}


}
