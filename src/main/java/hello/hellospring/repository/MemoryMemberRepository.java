package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L; //id에 자동숫자 넣어줌
	//동시성 문제로 인해 hashMap -> ConcurrentHashMap   /   long -> AtomicLong
	//동시성 문제? : 데이터를 변경했는데 반영되지않고 이상한 값이 반영되는 것


	@Override public Member save(final Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override public Optional<Member> findById(final Long id) {
		return Optional.ofNullable(store.get(id)); //null이 반환될거같으면 optional로 하면 나중에 data를 꺼내기 쉽다.
	}

	@Override public Optional<Member> findByName(final String name) {
		return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
		//store의 name이 member.getName 과 같은지 확인해보고 stream을 돌린다. 만약 같으면 반환, 아니면 null을 반환한다.
	}

	@Override public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();	//store을 싹 비운다.
	}

}
