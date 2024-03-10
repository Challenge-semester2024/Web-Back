package Challengesemester2024.domain.facility.facilityIntroduction.dto;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class GetFacilityIntroPKDto {
    FacilityIntroduction facilityIntroduction;
}
