package Challengesemester2024.Exception.collections.business;

import static Challengesemester2024.Exception.message.CommonExceptionMessage.DuplicateRecruitmentPerVolunteer;

public class DuplicateRecruitmentPerVolunteerException extends BusinessException {

    public DuplicateRecruitmentPerVolunteerException() {
        super(DuplicateRecruitmentPerVolunteer);
    }
}
