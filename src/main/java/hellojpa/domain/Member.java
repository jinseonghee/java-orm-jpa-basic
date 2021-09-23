package hellojpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //jpa가 처음 로딩될 때 jpa를 사용 한다는걸 인식 해 주기 위한 annotation
//@Table(name = "USER") DB에 테이블의 이름이 다를경우 설정해주는 annotation
public class Member {

    @Id // pk를 명시
    private Long id;
    private String name;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
