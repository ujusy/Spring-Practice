package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //java 8에 들어간 기능 없으면 null 반환 그냥 null 반환대신 optional 감싸서 반환
    List<Member> findAll();
}
