package net.itsplace.exception;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ExceptionRepository  extends JpaRepository<AppException, String> {

	Page<AppException> findAll(Specification<AppException> spec, Pageable pageable);
}
