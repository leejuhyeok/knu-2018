package kr.ac.knu.lecture.repository;

import kr.ac.knu.lecture.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, String> {
}
