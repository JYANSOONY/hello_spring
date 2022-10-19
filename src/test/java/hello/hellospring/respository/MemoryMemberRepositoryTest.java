package hello.hellospring.respository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest{

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드 하나가 끝날 떄 마다 동작하는 콜백 메서드
    public void afterEach(){
    repository.clearStore(); /**테스트 메소드는 순서 상관 없이 진행 되기 떄문에
                                메서드 실행이 끝날 떄 마다 레포지토리를 비워 줘야함(안그러면 값 충돌이 날 수 있음)*/
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 값을 꺼낼 때는 .get(); 으로 꺼내야함
        assertThat(member).isEqualTo(result);
//        Assertions.assertEquals(member, result);
    }

    @Test
    public void finByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
