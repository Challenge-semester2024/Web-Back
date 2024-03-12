package Challengesemester2024.businessProcess.facade.dto;

import Challengesemester2024.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CenterForeignKeyDto {
    FacilityIntroduction facility;
    Greetings greetings;
    RouteInfo routeInfo;
}
