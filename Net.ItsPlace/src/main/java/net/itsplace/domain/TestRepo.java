package net.itsplace.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestEnum, String> {

}
