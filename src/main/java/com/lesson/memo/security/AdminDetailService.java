package com.lesson.memo.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Service

public class AdminDetailService implements UserDetailsService{
	
	private final AdminRepository adminRepository;

    AdminDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // メールアドレスをもとにデータベースから管理者情報を検索
        Admin admin = (Admin) adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));

        // Spring Securityが扱えるUserDetailsオブジェクトに変換して返す
        return new User(
                admin.getEmail(),
                admin.getPassword(),
                new ArrayList<>() // 権限リスト
        );
    }

}
