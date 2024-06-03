package Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.service;


import Challengesemester2024.Exception.collections.business.DatabaseNotFoundException;
import Challengesemester2024.Exception.collections.business.DuplicateRecruitmentPerVolunteerException;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.repository.RecruitmentRepository;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.repository.RecruitmentAcceptRepository;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentWaitingList.dto.RequestAssignmentDto;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import Challengesemester2024.domain.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static Challengesemester2024.Exception.message.DbExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class RecruitmentAcceptServiceImpl implements RecruitmentAcceptService {
    private final RecruitmentAcceptRepository recruitmentAcceptRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final VolunteerRepository volunteerRepository;

    @Transactional
    @Override
    public void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId);
        if(recruitment==null) throw new DatabaseNotFoundException(RecruitmentDatabaseNotFoundException);

        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if( volunteer==null) throw new DatabaseNotFoundException(VolunteerDatabaseNotFoundException );

        if (!isVolunteerAlreadyAccepted(volunteerId, recruitmentDate)) {
            RecruitmentAccept acceptList = RecruitmentAccept.builder()
                    .volunteer(volunteer)
                    .recruitment(recruitment)
                    .recruitmentDates(List.of(recruitmentDate))
                    .build();
            recruitmentAcceptRepository.save(acceptList);
            recruitment.incrementCurrentApplicants();

        } else {
            throw new DuplicateRecruitmentPerVolunteerException();
        }
    }

    @Override
    @Transactional
    public void deleteVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        RecruitmentAccept recruitmentAccept = recruitmentAcceptRepository.findByRecruitmentIdAndVolunteerIdAndRecruitmentDate(recruitmentId, volunteerId, recruitmentDate)
                .orElseThrow(() -> new DatabaseNotFoundException(VolunteerDatabaseNotFoundException ));
        recruitmentAcceptRepository.delete(recruitmentAccept);
        Recruitment recruitment = recruitmentAccept.getRecruitment();
        recruitment.decrementCurrentApplicants();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isVolunteerAlreadyAccepted(Long volunteerId, LocalDate date) {
        return recruitmentAcceptRepository.existsByVolunteerIdAndRecruitmentDatesContains(volunteerId, date);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return recruitmentAcceptRepository.isDuplicateRecruitment(volunteer, requestAssignmentDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter) {
        return recruitmentAcceptRepository.findVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);
    }

}