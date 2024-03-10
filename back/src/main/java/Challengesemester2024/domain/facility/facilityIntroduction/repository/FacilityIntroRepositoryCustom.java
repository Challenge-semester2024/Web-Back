package Challengesemester2024.domain.facility.facilityIntroduction.repository;

import Challengesemester2024.domain.facility.facilityIntroduction.dto.GetFacilityIntroPKDto;
import Challengesemester2024.domain.facility.floorSize.model.FloorSize;

public interface FacilityIntroRepositoryCustom {
    // 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기
    public GetFacilityIntroPKDto getFacilityPk(String email);
    public void updateFacilityFloorSizeList(GetFacilityIntroPKDto getFacilityIntroPKDto, FloorSize floorSize);
}
