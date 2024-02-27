package Challengesemester2024.domain.routeInfo.service;

import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.config.DbInitConstants;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import Challengesemester2024.domain.routeInfo.repository.RouteInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteInfoServiceImpl implements RouteInfoService {
    private final RouteInfoRepository routeInfoRepository;
    @Override
    public RouteInfo createRouteInfo() {
        RouteInfo routeInfo = new RouteInfo(DbInitConstants.routeInfoInitMessage);
        try {
            routeInfoRepository.save(routeInfo);
        } catch (DataIntegrityViolationException e) {
            // 유니크 키 겹쳤을 때 에러 발생
            throw new DuplicateUniqueKeyException();
        }
        return routeInfo;
    }
}
