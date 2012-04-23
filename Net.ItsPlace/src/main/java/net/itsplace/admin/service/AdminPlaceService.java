package net.itsplace.admin.service;

import java.util.List;
import java.util.Map;

import net.itsplace.domain.Franchiser;

public interface AdminPlaceService {
	public List<Franchiser>  getFranchiserList(Map<String, Object> param);
}
