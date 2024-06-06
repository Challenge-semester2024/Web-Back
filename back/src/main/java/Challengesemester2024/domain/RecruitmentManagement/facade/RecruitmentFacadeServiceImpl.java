package Challengesemester2024.domain.RecruitmentManagement.facade;

import Challengesemester2024.Exception.collections.business.DuplicateRecruitmentPerVolunteerException;
import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.*;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.service.RecruitmentAcceptService;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.service.RecruitmentWaitingService;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.center.childCenter.service.ChildCenterService;
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
    public void updateRecruitment(RequestRecruitmentUpdateDto requestRecruitmentUpdateDto) {
        ChildCenter childCenter = getChildCenterPk();
        recruitmentService.updateRecruitment(requestRecruitmentUpdateDto, childCenter);
    }

    @Transactional(readOnly = true)
    @Override
    public RecruitmentPageDto getRecruitments(Pageable pageable) {
        ChildCenter fetchedChildCenter = getChildCenterPk();
        return recruitmentService.getRecruitments(pageable, fetchedChildCenter);
    }

    @Override
    public void addVolunteerToRecruitment(RequestAssignmentDto requestAssignmentDto) {
        Volunteer volunteer = getVolunteerPk();

        //유효성 검사 -> 현재 사용자가 해당 날짜에 이미 신청한 공고가 있을 시, 에러 처리 하도록 (증복 신청 불가능하게)
        if(recruitmentWaitingService.isDuplicateRecruitment(volunteer, requestAssignmentDto)) throw new DuplicateRecruitmentPerVolunteerException();
        if(recruitmentAcceptService.isDuplicateRecruitment(volunteer, requestAssignmentDto)) throw new DuplicateRecruitmentPerVolunteerException();

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
    public VolunteerByDateResponseDto getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate) {
        ChildCenter fetchedChildCenter = getChildCenterPk();
        List<Volunteer> waitingVolunteers = recruitmentWaitingService.getVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);
        List<Volunteer> acceptVolunteers = recruitmentAcceptService.getVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);

        List<VolunteerResponseDto> waitingVolunteerDtos = waitingVolunteers.stream()
                .map(volunteer -> VolunteerResponseDto.builder()
                        .id(volunteer.getId())
                        .name(volunteer.getName())
                        .build())
                .collect(Collectors.toList());

        List<VolunteerResponseDto> acceptVolunteerDtos = acceptVolunteers.stream()
                .map(volunteer -> VolunteerResponseDto.builder()
                        .id(volunteer.getId())
                        .name(volunteer.getName())
                        .build())
                .collect(Collectors.toList());

        return VolunteerByDateResponseDto.builder()
                .waitingVolunteers(waitingVolunteerDtos)
                .acceptVolunteers(acceptVolunteerDtos)
                .build();
    }

    @Override
    public void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        recruitmentAcceptService.acceptVolunteer(recruitmentId, volunteerId, recruitmentDate);
        recruitmentWaitingService.deleteFromWaitingList(recruitmentId, volunteerId, recruitmentDate);
    }

    @Override
    public void deleteVolunteerFromRecruitmentAccept(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        recruitmentAcceptService.deleteVolunteer(recruitmentId, volunteerId, recruitmentDate);
    }

    @Override
    public void deleteVolunteerFromRecruitmentWaiting(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        recruitmentWaitingService.deleteFromWaitingList(recruitmentId, volunteerId, recruitmentDate);
    }

    @Transactional(readOnly = true)
    @Override
    public RecruitmentPageDto findByNameWithPagination(RequestFindByName requestFindByName, Pageable pageable) {
        ChildCenter fetchedChildCenter = getChildCenterPk();
        return recruitmentService.findByNameWithPagination(requestFindByName.getRecruitmentName(), pageable, fetchedChildCenter);

    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthentication();
        return childCenterService.getChildCenterPk(authentication);
    }

    private Volunteer getVolunteerPk() {
        Authentication authentication = securityUtils.getAuthentication();
        return voluteerService.getVolunteeerPK(authentication);
    }
}
