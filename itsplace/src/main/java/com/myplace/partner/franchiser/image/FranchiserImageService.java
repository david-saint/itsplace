package com.myplace.partner.franchiser.image;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface FranchiserImageService {

	public List<FranchiserImage> getFranchiserImageList(FranchiserImage franchiserImage) throws DataAccessException;
	
	public FranchiserImage saveFranchiserImage(FranchiserImage franchiserImage) throws DataAccessException;
	
}
