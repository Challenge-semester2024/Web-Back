package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.Exception.collections.business.ChildCenterAlreadyExitsException;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import Challengesemester2024.domain.childCenter.repository.ChildCenterRepository;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildCenterServiceImpl implements ChildCenterService {
    private final ChildCenterRepository childCenterRepository;

    @Override
    public void register(SignUpDto.ChildCenter childCenterDto) {
        SignUpDto.ChildCenter childCenter = childCenterDto;

        Optional<ChildCenter> found = childCenterRepository.findByPhoneNumId(childCenter.getPhoneNum());
        if (found.isPresent()) throw new ChildCenterAlreadyExitsException();

        ChildCenter ChildCenter = new ChildCenter(childCenterDto.getCeoName(), childCenterDto.getCenterName(), childCenterDto.getPhoneNum(), childCenterDto.getRoadAddress(), childCenterDto.getDetailAddress());
        childCenterRepository.save(ChildCenter);
    }

}
