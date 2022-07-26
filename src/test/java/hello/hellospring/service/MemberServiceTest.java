package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);    //같은 인스턴스를 사용하기 위해 new로 각자 생성하는것이 아니라 외부에서 넣어준다 -> DI
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {  //test코드는 한글로 적어도 상관없음 -> 내부용
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
    public void 중복_회원_예외() {    //위는 반쪽짜리 테스트, 사실 중복회원은 오류가 발생하게 만드는 게 중요하다.
        //given
         Member member1 = new Member();
         member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        RuntimeException e = assertThrows(RuntimeException.class, () -> memberService.join(member2));
        //member2를 join하는 순간, Ill~ 에러가 생겨야한다.
        //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}