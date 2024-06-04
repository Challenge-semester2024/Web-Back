package Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.repository;

import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentRepositoryCustom {
    Page<Recruitment> findAllWithPagination(ChildCenter childCenter, Pageable pageable);
    Page<Recruitment> findByNameWithPagination(String recruitmentName, ChildCenter fetchedChildCenter, Pageable pageable);
}
