package me.Lee.springstudy.repository;

import me.Lee.springstudy.DAO.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
