package net.itsplace.repository;

import java.util.List;

import net.itsplace.domain.Bascd;
import net.itsplace.domain.GroupBascd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


public interface GroupBaseRepository  extends JpaRepository<GroupBascd, Integer> {

	/*@Query("select B from PBASCD B Where  B.isDelete = 'N' ")
	List<Bascd> findbYi();*/
	
	List<GroupBascd> findByIsDelete(Boolean isDelete);
}
