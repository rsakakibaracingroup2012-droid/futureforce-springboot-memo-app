package com.lesson.memo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller

public class AdminController {
	
	private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    AdminController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    } // パスワード暗号化用

    // 1. 登録画面の表示 
    @GetMapping("/admin/signup")
    String showSignupForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/signup"; // templates/admin/signup.html に対応
    }

    // 2. ユーザー登録処理
    @PostMapping("/admin/signup")
    String registerAdmin(@ModelAttribute Admin admin) {
        // パスワードを暗号化
        String hashedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);

        // データベースに保存
        adminRepository.save(admin);

        // ログイン画面へリダイレクト
        return "redirect:/admin/signin";
    }
    
 // 3. ログイン画面の表示 
    @GetMapping("/admin/signin")
    String showSigninForm() {
        return "admin/signin"; // templates/admin/signin.html に対応
    }

}
