package Challengesemester2024.domain.center.likeChildCenter.repository;


import Challengesemester2024.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import Challengesemester2024.domain.center.likeChildCenter.model.LikeCenter;


public interface LikeCenterRepositoryCustom {
    LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
}
