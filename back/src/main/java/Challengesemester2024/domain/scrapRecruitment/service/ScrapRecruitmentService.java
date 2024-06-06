package Challengesemester2024.domain.scrapRecruitment.service;


import Challengesemester2024.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import Challengesemester2024.domain.scrapRecruitment.model.ScrapRecruitment;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.util.List;

public interface ScrapRecruitmentService {
    ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto scrapRecruitmentDto);
    void createScrapRecruitment(ScrapRecruitmentDto fethedScrapRecruitmentDto);
    void deleteScrapRecruitment(ScrapRecruitment srapRecruitment);
    List<ScrapRecruitment> findByVolunteer(Volunteer volunteer);
    List<ScrapRecruitment> findTop3ByVolunteerOrderByIdDesc(Volunteer volunteer);
}
