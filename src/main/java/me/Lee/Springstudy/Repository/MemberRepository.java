package me.Lee.Springstudy.Repository;

import me.Lee.Springstudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // name 으로 값을 찾기
    Optional<Member> findByName(String name);
}
