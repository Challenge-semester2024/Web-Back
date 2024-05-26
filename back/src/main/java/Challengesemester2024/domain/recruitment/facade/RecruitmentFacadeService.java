package Challengesemester2024.domain.recruitment.facade;

import Challengesemester2024.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.recruitment.dto.RequestRecruitmentDto;
import org.springframework.data.domain.Pageable;

public interface RecruitmentFacadeService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto);
    RecruitmentPageDto getRecruitments(Pageable pageable);
}
