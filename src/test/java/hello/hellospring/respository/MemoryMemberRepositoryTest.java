package hello.hellospring.respository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 값을 꺼낼 때는 .get(); 으로 꺼내야함
        assertThat(member).isEqualTo(result);
//        Assertions.assertEquals(member, result);
    }

//    @Test
//    public void finByName(){
//        Member member1 = new Member();
//        member1.setName("spring1");
//    }
}
