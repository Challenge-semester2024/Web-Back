package Challengesemester2024.domain.recruitment.facade;

import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import Challengesemester2024.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentFacadeServiceImpl implements RecruitmentFacadeService{
    private final SecurityUtils securityUtils;
    private final ChildCenterService childCenterService;
    private final RecruitmentService recruitmentService;
    @Override
    public void createRecruitment(RequestRecruitmentDto requestRecruitmentDto) {
        ChildCenter childCenter = getChildCenterPk();
        recruitmentService.createRecruitment(requestRecruitmentDto, childCenter);
    }

    @Override
    public RecruitmentPageDto getRecruitments(Pageable pageable) {
        return recruitmentService.getRecruitments(pageable);
    }

    private ChildCenter getChildCenterPk(){
        Authentication authentication = securityUtils.getAuthenticationEmail();
        return childCenterService.getChildCenterPk(authentication);
    }
}
