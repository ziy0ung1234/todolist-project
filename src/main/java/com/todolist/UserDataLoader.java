package com.todolist;

import com.todolist.entity.User;
import com.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 서버 시작시 유저 생성 먼저 해놓기 위해 만든 클래스
 * */
@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("ziy0ung", "12345678"));
        userRepository.save(new User("ziho", "12345678"));
        userRepository.save(new User("zisooo", "12345678"));
    }
}
