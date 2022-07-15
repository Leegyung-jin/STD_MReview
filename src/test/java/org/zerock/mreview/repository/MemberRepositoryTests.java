package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMember(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r"+i+"@zerock.org")
                    .pw("1111")
                    .nickname("reviewer"+i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {
        Long mid = 4L;  // Member mid

        Member member = Member.builder().mid(mid).build();

        System.out.println("===================== member: " + member);

//        memberRepository.deleteById(mid);
//        reviewRepository.deleteByMember(member);

        // 순서 주의: FK를 가지는 Review를 먼저 삭제한 후 PK인 Member를 삭제해야한다.
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
