package com.crm.repository;

import com.crm.model.entities.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {
    @Modifying
    @Query("DELETE FROM Home h WHERE h.id = :id")
    void delete(@Param("id") int id);
}
