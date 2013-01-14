package net.itsplace.repository;

import net.itsplace.domain.PlaceComment;
import net.itsplace.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCommentRepository  extends JpaRepository<PlaceComment, Integer> {

}
