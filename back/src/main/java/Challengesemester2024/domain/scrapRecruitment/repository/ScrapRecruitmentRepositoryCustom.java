package Challengesemester2024.domain.scrapRecruitment.repository;

import Challengesemester2024.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import Challengesemester2024.domain.scrapRecruitment.model.ScrapRecruitment;

public interface ScrapRecruitmentRepositoryCustom {
    ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto recruitmentDto);
}
