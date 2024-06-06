package Challengesemester2024.domain.RecruitmentManagement.facade;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.*;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RecruitmentFacadeService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto);
    void updateRecruitment(RequestRecruitmentUpdateDto requestRecruitmentUpdateDto);
    RecruitmentPageDto getRecruitments(Pageable pageable);
    void addVolunteerToRecruitment(RequestAssignmentDto requestAssignmentDto);
    VolunteerByDateResponseDto getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate);
    void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    RecruitmentPageDto findByNameWithPagination(RequestFindByName requestFindByName, Pageable pageable);
    void deleteVolunteerFromRecruitmentAccept (Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    void deleteVolunteerFromRecruitmentWaiting (Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
}
