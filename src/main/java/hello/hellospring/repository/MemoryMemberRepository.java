package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //실무에선 HashMap이 동시성 문제가 있지만 예시니까 일단 사용
    private static long sequence = 0L; //키값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버에 아이디 값을 세팅해주고
        store.put(member.getId(), member); //스토어에 저장을 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //과거엔  return store.get(id); 써줬지만 NULL처리 떄문에 Optional써줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //멤버의 네임과 매개변수의 name이 같은가 찾기
                .findAny();//하나라고 찾으면 반환 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 member들이 반환
    }
}
