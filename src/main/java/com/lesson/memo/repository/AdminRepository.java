package com.lesson.memo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lesson.memo.model.Admin;

public interface AdminRepository extends JpaRepository <Admin, Long>{
    
    // メールアドレスでAdminを検索するメソッド
    Optional <Admin> findByEmail(String email);
}