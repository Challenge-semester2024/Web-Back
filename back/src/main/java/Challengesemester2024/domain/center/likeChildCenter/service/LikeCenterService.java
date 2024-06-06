package Challengesemester2024.domain.center.likeChildCenter.service;

import Challengesemester2024.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import Challengesemester2024.domain.center.likeChildCenter.model.LikeCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;

import java.util.List;

public interface LikeCenterService {
    void createLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
    void deleteLikeCenter(LikeCenter likeCenter);
    LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
    List<LikeCenter> findByVolunteer(Volunteer volunteer);

}
