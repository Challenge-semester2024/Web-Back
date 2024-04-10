package Challengesemester2024.domain.routeInfo.dto;

import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateRouteInfoDto {
    RouteInfo oldRouteInfo;
    RouteInfo newRouteInf0;
}
