package hellojpa;

import hellojpa.domain.Member;
import hellojpa.enums.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello" ); //EntityManagerFactory 실행시점에 하나만 생성

        EntityManager em = emf.createEntityManager(); //EntityManager는 일관적인 단위의 작업을 할 때 항상 만들어 줘야 한다.

        //jpa에서는 transaction이 중요. 모든 변경이 있을 때는 transaction 내에서 이루어 져야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);
             */

            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA"); //EntityManager가 관리 해서 변경을 감지하기 때문에 em.persist(member)  다시 저장하지 않아도 값이 update
             */

            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class) //JPQL
                        .setFirstResult(0) //페이징 사용할 경우 첫 페이지5에서 8까지 가져오라는 의미
                        .setMaxResults(8)
                        .getResultList();

            for(Member member : result) {
                System.out.println("member.name = " +member.getName());
            }
            */

            /*
            //비영속 - persistenceContext에 속하지 않음
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 - persistenceContext에서 관리(엔티티를 영구 저장한다는 뜻)
            System.out.println(" ==== BEFORE ====");
            em.persist(member); //1차 캐시에 저장 - 1차 캐시가 영속성 컨테스트라고 보면 됨
            System.out.println(" ==== AFTER ===="); //실제로 BEFOR, AFTER이 모두 돈 다음 쿼리실행.
                                                    //영속 상태(PersistenceContext에서 관리되어지는 상태)가 된다고 바로 DB에 날라가는게 아니라 commit 직전에 날라감
            */

            /*
            //영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(151L, "B");

            em.persist(member1);
            em.persist(member2);
            */

            /*
            //flush 사용
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush(); //flush를 사용하면 데이터베이스에 바로 값이 저장된다.
                        //JPQL 쿼리 실행시 비영속 상태일때 쿼리가 조회되지 않으므로 JPQL 쿼리문이 실행 되기 전 무조건 flush를 날림

            */

            /*
            //준영속 상태
            Member member = em.find(Member.class, 150L); //find만 해줘도 db에 조회에 봤을때 1차 캐시가 없으면 1차 캐시에 저장해 영속상태가 됨
            member.setName("AAAAA");

            em.detach(member); //위에 값을 변경했는데도 detach를 사용해 영속상태인걸, 준영속 상태로 만들어 jpa에서 관리하지 않도록 만들어 update query가 날라가지 않음
            //em.clear(); 영속성 컨테스트를 완전히 초기화로 만들어 준영속 상태로 만듬

            System.out.println("===============================");


             */

            Member member = new Member();
            member.setId(3L);
            member.setUsername("C");
            member.setRoleType(RoleType.USER); //EnumType.ORDINAL을 사용할 경우 db에 column을 추가할때 기존 데이터의 Enum값이 존재하므로 중복된 값이
                                               //저장될 수 있으므로 EnumType.STRING으로 사용하여 명시적으로 볼 수 있게 해주는게 좋다.

            em.persist(member);

            tx.commit();


        } catch ( Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
