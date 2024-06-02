package Challengesemester2024.domain.RecruitmentManagement.facade;

import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.service.RecruitmentAcceptService;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RequestAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.service.RecruitmentWaitingService;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.service.RecruitmentService;
import Challengesemester2024.domain.volunteer.dto.VolunteerResponseDto;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import Challengesemester2024.domain.volunteer.service.VoluteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentFacadeServiceImpl implements RecruitmentFacadeService{
    private final SecurityUtils securityUtils;
    private final ChildCenterService childCenterService;
    private final RecruitmentService recruitmentService;
    private final VoluteerService voluteerService;
    private final RecruitmentWaitingService recruitmentWaitingService;
    private final RecruitmentAcceptService recruitmentAcceptService;

    @Override
    public void createRecruitment(RequestRecruitmentDto requestRecruitmentDto) {
        ChildCenter childCenter = getChildCenterPk();
        recruitmentService.createRecruitment(requestRecruitmentDto, childCenter);
    }

    @Override
    public RecruitmentPageDto getRecruitments(Pageable pageable) {
        return recruitmentService.getRecruitments(pageable);
    }

    @Override
    public void addVolunteerToRecruitment(RequestAssignmentDto requestAssignmentDto) {
        Volunteer volunteer = getVolunteerPk();
        Recruitment recruitment = recruitmentService.getRecruitmentById(requestAssignmentDto.getRecruitmentId());

        RecruitmentAssignmentDto recruitmentAssignmentDto = RecruitmentAssignmentDto.builder()
                .recruitment(recruitment)
                .volunteer(volunteer)
                .recruitmentDates(requestAssignmentDto.getRecruitmentDates())
                .build();

        recruitmentWaitingService.addVolunteerToRecruitment(recruitmentAssignmentDto);
    }


    @Transactional(readOnly = true)
    @Override
    public List<VolunteerResponseDto> getVolunteersByDate(LocalDate date) {
        List<Volunteer> volunteers = recruitmentWaitingService.getVolunteersByDate(date);
        return volunteers.stream()
                .map(volunteer -> new VolunteerResponseDto(volunteer.getId(), volunteer.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        recruitmentAcceptService.acceptVolunteer(recruitmentId, volunteerId, recruitmentDate);
        recruitmentWaitingService.deleteFromWaitingList(recruitmentId, volunteerId, recruitmentDate);
    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthentication();
        return childCenterService.getChildCenterPk(authentication);
    }

    private Volunteer getVolunteerPk(){
        Authentication authentication = securityUtils.getAuthentication();
        return voluteerService.getVolunteeerPK(authentication);
    }
}
