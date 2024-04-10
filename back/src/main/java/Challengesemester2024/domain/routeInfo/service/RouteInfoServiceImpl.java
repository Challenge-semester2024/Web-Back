package Challengesemester2024.domain.routeInfo.service;

import Challengesemester2024.config.constant.DbInitConstants;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.routeInfo.dto.UpdateRouteInfoDto;
import Challengesemester2024.domain.routeInfo.repository.RouteInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteInfoServiceImpl implements RouteInfoService {
    private final RouteInfoRepository routeInfoRepository;
    @Override
    public RouteInfo createRouteInfo() {
        RouteInfo routeInfo = RouteInfo.builder()
                .memo(DbInitConstants.routeInfoInitMessage)
                .build();

        routeInfoRepository.save(routeInfo);
        return routeInfo;
    }

    @Override
    public void updateRouteInfo(UpdateRouteInfoDto updateRouteInfoDto) {
        routeInfoRepository.delete(updateRouteInfoDto.getOldRouteInfo());
        routeInfoRepository.save(updateRouteInfoDto.getNewRouteInf0());
    }
}
