package net.itsplace.repository;

import net.itsplace.domain.Category;
import net.itsplace.domain.PlaceComment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, String> {

}
