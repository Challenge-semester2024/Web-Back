package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.service;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentWaitingService {
    void addVolunteerToRecruitment(RecruitmentAssignmentDto recruitmentAssignmentDto);
    List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter);
    void deleteFromWaitingList(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
}