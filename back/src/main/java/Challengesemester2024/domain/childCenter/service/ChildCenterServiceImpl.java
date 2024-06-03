package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.Exception.collections.business.CenterNotFoundException;
import Challengesemester2024.Exception.collections.business.ChildCenterAlreadyExitsException;
import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.businessProcess.auth.web.dto.WebSignUpDto;
import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.domain.childCenter.dto.put.RequestFindChildCenterDto;
import Challengesemester2024.domain.childCenter.dto.put.ResponseChildCenterDto;
import Challengesemester2024.domain.childCenter.repository.ChildCenterRepository;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildCenterServiceImpl implements ChildCenterService {
    private final ChildCenterRepository childCenterRepository;

    @Override
    public void checkExits(WebSignUpDto.centerInfo childCenterDto) {
        Optional<ChildCenter> found = childCenterRepository.findByPhoneNumId(childCenterDto.getPhoneNum());
        if (found.isPresent()) throw new ChildCenterAlreadyExitsException();
    }

    @Override
    public ChildCenter getChildCenterPk(Authentication authentication) {
        return childCenterRepository.getChildCenterPk(authentication);
    }

    @Override
    public List<ResponseChildCenterDto> findChildCenter(RequestFindChildCenterDto requestDto) {
        List<ChildCenter> centers;

        if (requestDto.isFindWordStandard()) {
            centers = childCenterRepository.findByRoadAddressContaining(requestDto.getRoadAddress());
        } else {
            centers = childCenterRepository.findByCenterNameContaining(requestDto.getChildCenterName());
        }

        if ( centers.isEmpty()) {
            throw new CenterNotFoundException();
        }

        return centers.stream()
                .map(center -> ResponseChildCenterDto.builder()
                        .id(center.getId())
                        .centerName(center.getCenterName())
                        .roadAddress(center.getRoadAddress())
                        .detailAddress(center.getDetailAddress())
                        .phoneNumber(center.getPhoneNumId())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public ChildCenter findById(Long id) {
        ChildCenter childCenter = childCenterRepository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException());

        return childCenter;
    }

    @Override
    public ManagerRegisterDto register(WebSignUpDto.centerInfo centerInfo, CenterForeignKeyDto centerForeignKeyDto, String s3url) {

        ChildCenter childCenter = ChildCenter.builder()
                .phoneNumId(centerInfo.getPhoneNum())
                .ceoName(centerInfo.getCeoName())
                .centerName(centerInfo.getCenterName())
                .roadAddress(centerInfo.getRoadAddress())
                .detailAddress(centerInfo.getDetailAddress())
                .certificate(s3url)
                .facilityIntroduction(centerForeignKeyDto.getFacility())
                .greetings(centerForeignKeyDto.getGreetings())
                .routeInfo(centerForeignKeyDto.getRouteInfo())
                .build();

        try {
            childCenterRepository.save(childCenter);
        } catch (DataIntegrityViolationException e) {
            // 예외가 발생했을 때 처리할 코드
            throw new DuplicateUniqueKeyException();
        }

        ManagerRegisterDto managerRegisterDto = ManagerRegisterDto.builder()
                .childCenter(childCenter)
                .build();

        return managerRegisterDto;
    }


}
