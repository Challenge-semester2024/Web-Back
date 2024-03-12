package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.Exception.collections.business.ChildCenterAlreadyExitsException;
import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.businessProcess.facade.dto.CenterForeignKeyDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;
import Challengesemester2024.domain.childCenter.repository.ChildCenterRepository;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildCenterServiceImpl implements ChildCenterService {
    private final ChildCenterRepository childCenterRepository;

    @Override
    public void checkExits(SignUpDto.centerInfo childCenterDto) {
        Optional<ChildCenter> found = childCenterRepository.findByPhoneNumId(childCenterDto.getPhoneNum());
        if (found.isPresent()) throw new ChildCenterAlreadyExitsException();
    }

    @Override
    public ManagerRegisterDto register(SignUpDto.centerInfo centerInfo, CenterForeignKeyDto centerForeignKeyDto, S3urlDto s3urlDto) {

        ChildCenter childCenter = ChildCenter.builder()
                .phoneNumId(centerInfo.getPhoneNum())
                .ceoName(centerInfo.getCeoName())
                .centerName(centerInfo.getCenterName())
                .roadAddress(centerInfo.getRoadAddress())
                .detailAddress(centerInfo.getDetailAddress())
                .certificate(s3urlDto.getS3url())
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
