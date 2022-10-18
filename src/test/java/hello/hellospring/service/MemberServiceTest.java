package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository; /*new로 객체를 만들어서 사용하면 멤버서비스의 repository랑 여기랑 객체가 다름*/

    @BeforeEach
    public void beforeEach(){ /*각 테스트들을 실행 하기전에 실행*/
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); /*멤버서비스의 repository랑 같은 MemoryMemberRepository를 사용*/
    }

    @AfterEach //메소드 하나가 끝날 떄 마다 동작하는 콜백 메서드
    public void afterEach(){
        memberRepository.clearStore(); /**테스트 메소드는 순서 상관 없이 진행 되기 떄문에
         메서드 실행이 끝날 떄 마다 레포지토리를 비워 줘야함(안그러면 값 충돌이 날 수 있음)*/
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
       assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");/*join 로직이 실행이 될 때 IllegalStateException 터져야 함*/
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1212212");
//        }
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}