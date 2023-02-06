package com.ezen.persistence;

import com.ezen.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.Member;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "select m from Member m where m.name=:name and m.phone=:phone")
    Member findMemberId(String name, String phone); // 아이디 찾기

    @Query(value = "select m from Member m where m.username=:username and m.phone=:phone")
    Member findMemberPwd(String username, String phone); // 비밀번호 찾기
}
