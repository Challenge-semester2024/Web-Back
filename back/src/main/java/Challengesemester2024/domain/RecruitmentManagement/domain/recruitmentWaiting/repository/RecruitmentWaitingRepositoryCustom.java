package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.util.List;

public interface RecruitmentWaitingRepositoryCustom {
    List<Volunteer> findVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildenter);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
}
