package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.service;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentWaitingService {
    void addVolunteerToRecruitment(RecruitmentAssignmentDto recruitmentAssignmentDto);
    List<Volunteer> getVolunteersByDate(LocalDate date);
    void deleteFromWaitingList(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
}