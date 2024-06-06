package Challengesemester2024.domain.scrapRecruitment.facade;

import Challengesemester2024.domain.center.childCenter.dto.put.RequestFindWordDto;
import Challengesemester2024.domain.scrapRecruitment.dto.RequestScrapRecruitmentDto;
import Challengesemester2024.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;

import java.util.List;

public interface ScrapRecruitmentFacadeService {
    void scrapRecruitment(RequestScrapRecruitmentDto requestScrapRecruitmentDto);
    List<ResponseRecruitmentWithScrapToAppDto> searchRecruitmentWithScrap(RequestFindWordDto requestFindWordDto);
    List<ResponseRecruitmentWithScrapToAppDto> getScrappedRecruitments();
}
