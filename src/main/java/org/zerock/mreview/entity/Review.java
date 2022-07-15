package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;


    /* Movie Member를 양쪽으로 참조하는 구조이기 때문에 @ManyToOne 사용*/
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    /* Movie Member를 양쪽으로 참조하는 구조이기 때문에 @ManyToOne 사용*/
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade;
    private String text;
}
