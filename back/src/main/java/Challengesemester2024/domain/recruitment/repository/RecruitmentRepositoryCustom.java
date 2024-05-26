package Challengesemester2024.domain.recruitment.repository;

import Challengesemester2024.domain.recruitment.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentRepositoryCustom {
    Page<Recruitment> findAllWithPagination(Pageable pageable);
}
