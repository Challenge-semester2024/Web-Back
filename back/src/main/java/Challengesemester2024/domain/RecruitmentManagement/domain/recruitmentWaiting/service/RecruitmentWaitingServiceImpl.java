package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.service;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaiting.repository.RecruitmentWaitingRepository;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentWaitingServiceImpl implements RecruitmentWaitingService {
    private final RecruitmentWaitingRepository recruitmentWaitingRepository;

    @Override
    @Transactional
    public void addVolunteerToRecruitment(RecruitmentAssignmentDto recruitmentAssignmentDto) {
        RecruitmentWaiting recruitmentWaiting = RecruitmentWaiting.builder()
                .volunteer(recruitmentAssignmentDto.getVolunteer())
                .recruitment(recruitmentAssignmentDto.getRecruitment())
                .recruitmentDates(recruitmentAssignmentDto.getRecruitmentDates())
                .build();

        recruitmentWaitingRepository.save(recruitmentWaiting);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter) {
        return recruitmentWaitingRepository.findVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);
    }


    @Transactional
    @Override
    public void deleteFromWaitingList(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        RecruitmentWaiting recruitmentWaiting = recruitmentWaitingRepository.findByRecruitmentIdAndVolunteerIdAndRecruitmentDatesContains(recruitmentId, volunteerId, recruitmentDate)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list entry not found"));

        recruitmentWaitingRepository.delete(recruitmentWaiting);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return recruitmentWaitingRepository.isDuplicateRecruitment(volunteer, requestAssignmentDto);
    }
}