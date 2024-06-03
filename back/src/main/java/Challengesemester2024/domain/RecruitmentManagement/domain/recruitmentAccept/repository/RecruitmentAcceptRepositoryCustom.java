package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RequestAssignmentDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecruitmentAcceptRepositoryCustom {
    List<Volunteer> findVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildenter);
    Optional<RecruitmentAccept> findByRecruitmentIdAndVolunteerIdAndRecruitmentDate(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
}
