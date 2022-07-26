package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository { //db에 담는 로직
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name); //optional- 자바8부터 나왔고 null값이 반환될때 optional로 감싸서 내보낸다.
	List<Member> findAll();
}
