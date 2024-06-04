package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.service;


import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.*;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitmentService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto, ChildCenter childCenterPk);
    void updateRecruitment(RequestRecruitmentUpdateDto requestRecruitmentUpdateDto, ChildCenter childCenter);
    RecruitmentPageDto getRecruitments(Pageable pageable, ChildCenter fetchedChildCenter);
    List<RecruitmentSummaryDto> getRecruitmentSummariesByChildCenter(ChildCenter childCenter);
    Recruitment getRecruitmentById(Long id);
    RecruitmentDetailDto getRecruitmentDetail(Long id);
    RecruitmentPageDto findByNameWithPagination(String recruitmentName, Pageable pageable, ChildCenter fetchedChildCenter);
}
