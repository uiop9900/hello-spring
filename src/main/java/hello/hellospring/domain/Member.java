package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	//db가 알아서 생성해준다.
	private Long id;
	private String name;

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
