package Challengesemester2024.domain.recruitment.service;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.recruitment.model.DaysOfWeek;
import Challengesemester2024.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

import static Challengesemester2024.config.constant.DbInitConstants.*;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public void createRecruitment(RequestRecruitmentDto rqRecruitmentDto, ChildCenter childCenterPk) {
        LocalDate todayDate = LocalDate.now();

        String detailInfo = rqRecruitmentDto.getDetailInfo();
        if(detailInfo==null || detailInfo==""){
            detailInfo=initRecruitmentDetailInfo;
        }

        Recruitment newRecruitment = Recruitment.builder()
                .name(rqRecruitmentDto.getName())
                .recruitmentStartDate(todayDate)
                .recruitmentEndDate(rqRecruitmentDto.getRecruitmentEndDate())
                .isTimeExits(rqRecruitmentDto.isTimeExits())
                .startTime(rqRecruitmentDto.getStartTime())
                .endTime(rqRecruitmentDto.getEndTime())
                .startDate(rqRecruitmentDto.getStartDate())
                .endDate(rqRecruitmentDto.getEndDate())
                .isRepeatedDate(rqRecruitmentDto.getIsRepeatedDate())
                .repeatedDays(convertToDaysOfWeek(rqRecruitmentDto.getRepeatedDays()))
                .view(initRecruitmentView)
                .currentApplicants(initRecruitmentCurrentApplicants)
                .totalApplicants(rqRecruitmentDto.getTotalApplicants())
                .detailInfo(detailInfo)
                .childCenter(childCenterPk)
                .build();

        recruitmentRepository.save(newRecruitment);
    }

    @Override
    public RecruitmentPageDto getRecruitments(Pageable pageable) {
        Page<Recruitment> recruitmentPage = recruitmentRepository.findAll(pageable);
        List<Recruitment> recruitments = recruitmentPage.getContent();
        long totalElements = recruitmentPage.getTotalElements();
        int totalPages = recruitmentPage.getTotalPages();
        return new RecruitmentPageDto(recruitments, totalElements, totalPages);
    }

    private DaysOfWeek convertToDaysOfWeek(List<Boolean> repeatedDays) {
        if (repeatedDays.size() != 7) {
            throw new IllegalArgumentException("반복 요일 리스트는 7개의 항목을 가져야 합니다.");
        }

        DaysOfWeek daysOfWeek = new DaysOfWeek();
        daysOfWeek.setSunday(repeatedDays.get(0));
        daysOfWeek.setMonday(repeatedDays.get(1));
        daysOfWeek.setTuesday(repeatedDays.get(2));
        daysOfWeek.setWednesday(repeatedDays.get(3));
        daysOfWeek.setThursday(repeatedDays.get(4));
        daysOfWeek.setFriday(repeatedDays.get(5));
        daysOfWeek.setSaturday(repeatedDays.get(6));

        return daysOfWeek;
    }

}
