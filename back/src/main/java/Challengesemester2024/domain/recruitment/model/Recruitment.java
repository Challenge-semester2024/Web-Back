package Challengesemester2024.domain.recruitment.model;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name; //봉사이름

    @Column(nullable = false, length = 255)
    private Time startTime; //봉사시작시간

    @Column(nullable = false, length = 255)
    private Time endTime; //봉사종료시간

    @Column(nullable = false, length = 255)
    private Date startDate; //봉사시작일

    @Column(nullable = false, length = 255)
    private Date endDate; //봉사종료일

    private boolean isRepeatedDate; // 특정 요일만 허용하는지 판단할 변수

    @NotNull
    private int view; //조회

    @NotNull
    private int totalApplicants; //마감인원

    @NotNull
    private int currentApplicants; //현재 신청인원

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detailInfo; //해당 봉사 공고의 상제 정보.

    @ManyToOne
    @JoinColumn(name = "child_center_id")
    private ChildCenter childCenter;

}


//반복설정 -> false면 신경 안 써도 됨
//        -> true면 해당요일에서 어떤 원소가 true인지 확인
//해당요일 일,월,화,수,목,금,토의 순으로 boolean 받기
//시간선택 -> true면 봉시사작시간, 봉사종료시간에 값 들어옴
//        -> false면 봉사시작시간, 봉사종료시간에 값 안들어옴 -> 내가 임의의 -값 넣어줘야 할 것 같음

