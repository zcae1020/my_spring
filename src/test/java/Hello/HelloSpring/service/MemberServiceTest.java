package Hello.HelloSpring.service;

import Hello.HelloSpring.domain.Member;
import Hello.HelloSpring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clear();
    }


    @Test
    void join() {
        Member member = new Member();
        member.setName("aa");

        Long result = memberService.join(member);

        Member find = memberService.findOne(result).get();
        assertThat(find).isEqualTo(member);
    }

    @Test
    void checkDuplicate(){
        Member m1 = new Member();
        m1.setName("A");

        Member m2 = new Member();
        m2.setName("A");

        memberService.join(m1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 멤버입니다.");

        /*try {
            MemberService.join(m2);
            fail();
        }catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 멤버입니다.");
        }*/
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("aa");

        Member member2 = new Member();
        member2.setName("aa");
    }

    @Test
    void findOne() {
    }
}