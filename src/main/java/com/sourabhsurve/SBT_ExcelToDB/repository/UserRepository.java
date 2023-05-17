package com.sourabhsurve.SBT_ExcelToDB.repository;

import com.sourabhsurve.SBT_ExcelToDB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
