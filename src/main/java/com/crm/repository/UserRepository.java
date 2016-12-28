package com.crm.repository;


import com.crm.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring data repository for retrieving {@link User}s.
 */

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String mail);

    @Query("SELECT c FROM User c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(c.mail) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    Page<User> findUsers(@Param("searchTerm") String searchTerm, Pageable pageRequest);
}