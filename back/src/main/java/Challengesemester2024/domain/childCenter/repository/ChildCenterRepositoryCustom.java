package Challengesemester2024.domain.childCenter.repository;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import org.springframework.security.core.Authentication;

public interface ChildCenterRepositoryCustom { //더 복잡한 쿼리사용을 위해 커스텀 레포 만듦
    ChildCenter getChildCenterPk(Authentication authentication);
}

