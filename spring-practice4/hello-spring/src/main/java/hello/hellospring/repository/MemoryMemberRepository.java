package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // key: 회원의 id, value는 memeber
    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제 있으니 concurrentHashMap 사용
    private static long sequence = 0L; // 0,1,2 key 값 생성 실무에서는 동시성 문제로 autom long을 사용한다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id setting
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
/*        return store.get(id);  // 예전에는 이랬음 */
        return Optional.ofNullable(store.get(id)); //요즘에는 null 반환 확률 있으면 옵셔널 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values가 멤버. 멤버들 반환
    }

    public void clearStore() {
        store.clear();
    }
}
