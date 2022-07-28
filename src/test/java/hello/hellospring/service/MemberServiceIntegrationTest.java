package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //어차피 test는 끝쪽이라 제일 편리한 방법으로 불러와도 된다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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


}