package Challengesemester2024.domain.recruitment.service;


import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.recruitment.dto.RequestRecruitmentDto;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto, ChildCenter childCenterPk);
    RecruitmentPageDto getRecruitments(Pageable pageable);
}
