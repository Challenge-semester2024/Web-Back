package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.service;


import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentDetailDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto, ChildCenter childCenterPk);
    RecruitmentPageDto getRecruitments(Pageable pageable);
    List<Recruitment> getRecruitmentsByChildCenter(ChildCenter childCenter);
    List<RecruitmentSummaryDto> getRecruitmentSummariesByChildCenter(ChildCenter childCenter);
    Recruitment getRecruitmentById(Long id);
    RecruitmentDetailDto getRecruitmentDetail(Long id);
}
