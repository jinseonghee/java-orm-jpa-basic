package hellojpa.domain;

import hellojpa.enums.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity //jpa가 처음 로딩될 때 jpa를 사용 한다는걸 인식 해 주기 위한 annotation(name이 붙으면 객체의 이름을 jpa랑 매핑시킬때 다른 패키지의 객체 이름일 수도 있기 때문에 명시적 선언)
//@Table(name = "USER") DB에 테이블의 이름이 다를경우 매핑시켜주는 annotation
public class Member {

    @Id // pk를 명시
    private Long id;

    //@Column(unique = true, length = 10)  db에만 반영(ddl 생성기능)
    @Column(name = "name" , nullable = false) //nullable = false은 not null
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //DB에는 enum type을 사용하지 못하기 때문에 이 annotation 사용
    //@Enumerated(EnumType.ORDINAL) ordinal을 사용하면 순서의 숫자값으로 저장이 된다.(지양)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP) //TIMESTAMP은 Date와 Time 모두 사용
    private Date lastModifiedDate;

    @Lob //varchar2보다 많은 문자를 사용할 경우(String일 경우 clob으로 생성)
    private String description;

    @Transient //메모리에만 사용하고 db에선 사용하지 않을 때
    private int temp;

    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
