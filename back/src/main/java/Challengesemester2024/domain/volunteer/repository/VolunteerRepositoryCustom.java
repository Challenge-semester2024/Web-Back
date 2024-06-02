package Challengesemester2024.domain.volunteer.repository;

import Challengesemester2024.domain.volunteer.model.Volunteer;
import org.springframework.security.core.Authentication;

public interface VolunteerRepositoryCustom {
    Volunteer getVolunteerPk(Authentication authentication);
    Volunteer findVolunteerById(Long id);
}
