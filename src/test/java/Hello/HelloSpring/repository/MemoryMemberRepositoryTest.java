package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clear();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        Member member1 = new Member();
        member1.setName("s");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member m1 = new Member();
        m1.setName("a");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("b");
        repository.save(m2);

        Member result = repository.findByName("a").get();
        assertThat(m1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member m1 = new Member();
        m1.setName("a");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("b");
        repository.save(m2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
