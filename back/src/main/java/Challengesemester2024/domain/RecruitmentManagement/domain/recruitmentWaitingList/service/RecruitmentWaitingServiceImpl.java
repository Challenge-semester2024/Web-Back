package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.service;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RecruitmentAssignmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.model.RecruitmentWaiting;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.repository.RecruitmentWaitingRepository;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Volunteer> getVolunteersByDate(LocalDate date) {
        List<RecruitmentWaiting> recruitmentWaitings = recruitmentWaitingRepository.findByRecruitmentDate(date);
        return recruitmentWaitings.stream()
                .map(RecruitmentWaiting::getVolunteer)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deleteFromWaitingList(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        RecruitmentWaiting recruitmentWaiting = recruitmentWaitingRepository.findByRecruitmentIdAndVolunteerIdAndRecruitmentDatesContains(recruitmentId, volunteerId, recruitmentDate)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list entry not found"));

        recruitmentWaitingRepository.delete(recruitmentWaiting);
    }
}