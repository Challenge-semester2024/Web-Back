package Challengesemester2024.domain.center.childCenter.dto.get;

import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChildCenterFacilityInfoDto {
    private Greetings greeting;
    private List<DecadeYear> decadeYearList;
    private RouteInfo routeInfo;
}
