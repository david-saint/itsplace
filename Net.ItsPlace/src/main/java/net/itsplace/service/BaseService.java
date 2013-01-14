package net.itsplace.service;

import java.util.List;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.GroupBascd;
import net.itsplace.repository.BaseRepository;
import net.itsplace.repository.GroupBaseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminBaseService")
public class BaseService implements IBaseService{
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	@Autowired
	GroupBaseRepository groupBaseRepositoryRepo;
	@Autowired
	BaseRepository baseRepositoryRepo;

	@Override
	public List<GroupBascd> getGrpBascdList() {
		
//		return adminBaseDao.getGrpBascdList();
		return groupBaseRepositoryRepo.findByIsDelete(false);
	}



	@Override
	public List<Bascd> getBascdList(String grpCd) {
		//return adminBaseDao.getBascdList(grpCd);
		return baseRepositoryRepo.findByGrpcd(grpCd);
	}



	@Override
	public void saveBascd(Bascd bascd) {
		//adminBaseDao.saveBascd(bascd);
		baseRepositoryRepo.save(bascd);
	}



	@Override
	public void editBascd(Bascd bascd) {
		
		baseRepositoryRepo.save(bascd);
	}



	@Override
	public Bascd getBascd(int no) {

		return baseRepositoryRepo.findOne(no);
	}
}
