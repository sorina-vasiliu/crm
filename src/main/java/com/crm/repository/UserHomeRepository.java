package com.crm.repository;

import com.crm.model.entities.UserHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserHomeRepository extends JpaRepository<UserHome, Integer> {
    @Query("SELECT uh FROM UserHome uh WHERE uh.user.id IN :usersId ORDER BY uh.home.id DESC")
    List<UserHome> getUserHomeByUsersId(@Param("usersId") List<Integer> usersId);

    @Query("SELECT uh FROM UserHome uh WHERE uh.user.id = :userId ORDER BY uh.home.id DESC")
    List<UserHome> getUserHomeByUserId(@Param("userId") int userId);

    @Modifying
    @Query("DELETE FROM UserHome uh WHERE uh.home.id = :id")
    void delete(@Param("id") int id);
}
