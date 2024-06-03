package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.service;


import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RequestAssignmentDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentAcceptService {
    void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    void deleteVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isVolunteerAlreadyAccepted(Long volunteerId, LocalDate date);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
    List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter);
}