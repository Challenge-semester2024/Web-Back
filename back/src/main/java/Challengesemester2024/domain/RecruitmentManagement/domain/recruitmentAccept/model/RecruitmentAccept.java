package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "recruitment_accept")
public class RecruitmentAccept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_accept_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @ElementCollection
    @CollectionTable(name = "recruitment_accept_dates", joinColumns = @JoinColumn(name = "recruitment_accept_id"))
    @Column(name = "recruitment_date", nullable = false)
    private List<LocalDate> recruitmentDates;
}