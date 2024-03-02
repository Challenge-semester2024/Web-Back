package Challengesemester2024.domain.facilityIntroduction.service;

import Challengesemester2024.domain.facilityIntroduction.model.FacilityIntroduction;
import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    FacilityIntroduction createFacilityIntroduction();
}
