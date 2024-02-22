package Challengesemester2024.domain.childCenter.repository;

public interface ChildCenterRepositoryCustom { //더 복잡한 쿼리사용을 위해 커스텀 레포 만듦
    Long getCenterIdByManagerAccountId(String accountId);
}

