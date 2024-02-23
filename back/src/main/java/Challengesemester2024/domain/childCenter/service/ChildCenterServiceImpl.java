package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.Exception.collections.business.ChildCenterAlreadyExitsException;
import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import Challengesemester2024.domain.childCenter.repository.ChildCenterRepository;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChildCenterServiceImpl implements ChildCenterService {
    private final ChildCenterRepository childCenterRepository;

    @Override
    public void checkExits(SignUpDto.ChildCenter childCenterDto) {
        Optional<ChildCenter> found = childCenterRepository.findByPhoneNumId(childCenterDto.getPhoneNum());
        if (found.isPresent()) throw new ChildCenterAlreadyExitsException();
    }

    @Override
    public void register(SignUpDto.ChildCenter childCenterDto) {
        ChildCenter ChildCenter = new ChildCenter(childCenterDto.getCeoName(), childCenterDto.getCenterName(), childCenterDto.getPhoneNum(), childCenterDto.getRoadAddress(), childCenterDto.getDetailAddress());
        try {
            childCenterRepository.save(ChildCenter);
        } catch (DataIntegrityViolationException e) {
            // 예외가 발생했을 때 처리할 코드
            throw new DuplicateUniqueKeyException();
        }

    }


}
