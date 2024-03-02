package Challengesemester2024.domain.facilityIntroduction.service;

import Challengesemester2024.config.constant.DbInitConstants;
import Challengesemester2024.domain.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.facilityIntroduction.repository.FacilityIntroductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService{
    private final FacilityIntroductionRepository facilityRepository;
    @Override
    public FacilityIntroduction createFacilityIntroduction() {
        FacilityIntroduction facilityIntroduction = FacilityIntroduction.builder()
                .totalArea(DbInitConstants.facilityInitTotalArea)
                .build();

        facilityRepository.save(facilityIntroduction);
        return facilityIntroduction;
    }
}
