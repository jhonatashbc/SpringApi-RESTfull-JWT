package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

	List<Request> findAllByOwnerId(Long ownerId);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	int updateStatus(Long requestId, RequestState state);
	
	Page<Request> findAllByOwnerId(Long id, Pageable pageable);

	Page<Request> findAllByClientIdentificationNumber(String identificationNumber, Pageable pageable );

	Page<Request> findAllByClientId(Long id, Pageable pageable );
}
