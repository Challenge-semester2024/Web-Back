package Challengesemester2024.domain.center.likeChildCenter.dto;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCenterInfoDto {
    Volunteer volunteer;
    ChildCenter childCenter;
}
