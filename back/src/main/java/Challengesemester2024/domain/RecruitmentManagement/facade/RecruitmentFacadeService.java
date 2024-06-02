package Challengesemester2024.domain.RecruitmentManagement.facade;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RequestAssignmentDto;
import Challengesemester2024.domain.volunteer.dto.VolunteerResponseDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentFacadeService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto);
    RecruitmentPageDto getRecruitments(Pageable pageable);
    void addVolunteerToRecruitment(RequestAssignmentDto requestAssignmentDto);
    List<VolunteerResponseDto> getVolunteersByDate(LocalDate date);
    void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
}
