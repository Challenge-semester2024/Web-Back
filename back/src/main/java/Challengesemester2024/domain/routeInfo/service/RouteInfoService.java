package Challengesemester2024.domain.routeInfo.service;

import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;

public interface RouteInfoService {
    RouteInfo createRouteInfo();
    void updateRouteInfo(UpdateRouteInfoDto updateRouteInfoDto);
}
