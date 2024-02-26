package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.businessProcess.auth.dto.SignUpDto;

public interface ChildCenterService {
    public void register(SignUpDto.centerInfo childCenter, String fileUrl);
    public void checkExits(SignUpDto.centerInfo childCenter);
}
