package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.service;


import java.time.LocalDate;

public interface RecruitmentAcceptService {
    void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isVolunteerAlreadyAccepted(Long volunteerId, LocalDate date);
}