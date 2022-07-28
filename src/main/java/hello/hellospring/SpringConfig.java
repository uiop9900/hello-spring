package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 하나일경우, 생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JpaMemberRepository(em);
    }*/

/*    @Bean
    public TimeTraceAop TimeTraceAop() { //@Component를 통해서도 등록이 가능하지만, 일부러 명시하기 위해 여기서 빈으로 등록한다.
        return new TimeTraceAop();
    }*/

}
