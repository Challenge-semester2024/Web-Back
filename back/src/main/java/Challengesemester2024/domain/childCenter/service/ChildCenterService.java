package Challengesemester2024.domain.childCenter.service;

import Challengesemester2024.businessProcess.auth.dto.auth.SignUpDto;

public interface ChildCenterService {
    public void register(SignUpDto.centerInfo childCenter, String fileUrl);
    public void checkExits(SignUpDto.centerInfo childCenter);
}
