package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//mport org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        // 일종의 콜백
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spirng");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        //Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("SPRING1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("SPRING2");
        repository.save(member2);

        Member result = repository.findByName("SPRING1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("SPRING1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("SPRING2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //assertThat(result.size()).isEqualTo(3);

    }
}
