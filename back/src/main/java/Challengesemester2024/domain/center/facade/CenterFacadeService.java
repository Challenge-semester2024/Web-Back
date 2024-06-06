package Challengesemester2024.domain.center.facade;

import Challengesemester2024.domain.center.childCenter.dto.put.RequestFindWordDto;
import Challengesemester2024.domain.center.childCenter.dto.put.ResponseChildCenterDetailDto;
import Challengesemester2024.domain.center.childCenter.dto.put.ResponseChildCenterToAppDto;
import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.center.likeChildCenter.dto.RequestLikeCenterDto;

import java.util.List;

public interface CenterFacadeService {
    void addLikeCenter(RequestLikeCenterDto rerequestLikeCenterDto);
    List<ResponseChildCenterToAppDto> searchCenterWithLike();
    List<ChildCenter> findChildCenter(RequestFindWordDto requestFindWordDto);
    List<ResponseChildCenterDetailDto> convertResponseToWeb(List<ChildCenter> centers);
}
