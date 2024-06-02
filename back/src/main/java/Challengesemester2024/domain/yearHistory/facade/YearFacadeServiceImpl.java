package Challengesemester2024.domain.yearHistory.facade;

import Challengesemester2024.SpringSecurity.authentication.SecurityUtils;
import Challengesemester2024.domain.childCenter.model.ChildCenter;
import Challengesemester2024.domain.childCenter.repository.ChildCenterRepository;
import Challengesemester2024.domain.yearHistory.dto.decadeYear.RequestDecadeDataDto;
import Challengesemester2024.domain.yearHistory.dto.yearHistory.CreateYearHistoryDto;
import Challengesemester2024.domain.yearHistory.dto.decadeYear.CreateDecadeYearDto;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import Challengesemester2024.domain.yearHistory.model.YearHistory;
import Challengesemester2024.domain.yearHistory.repositroy.decadeYear.DecadeYearRepository;
import Challengesemester2024.domain.yearHistory.repositroy.year.YearHistoryRepository;
import Challengesemester2024.domain.yearHistory.service.decadeYear.DecadeYearService;
import Challengesemester2024.domain.yearHistory.service.year.YearHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YearFacadeServiceImpl implements YearFacadeService {
    private final DecadeYearRepository decadeYearRepository;
    private final DecadeYearService decadeYearService;
    private final YearHistoryService yearHistoryService;
    private final YearHistoryRepository yearHistoryRepository;
    private final ChildCenterRepository childCenterRepository;
    private final SecurityUtils securityUtils;

    @Override
    public void createYearHistory(List<RequestDecadeDataDto> yearDataList) {
        Authentication authentication = securityUtils.getAuthentication();
        ChildCenter fetchedChildCenter = childCenterRepository.getChildCenterPk(authentication);

        //1. decadeStartYear 행으로 된 DecadeYear Repository 행 있나 확인
        for (RequestDecadeDataDto requestDecadeData : yearDataList) {

            // decadeStartYear를 가진 DecadeYear 엔티티가 데이터베이스에 존재하는지 확인
            DecadeYear decadeYear = decadeYearRepository.findByDecadeStartYear(requestDecadeData.getDecadeStartYear());

            if (decadeYear == null) { //null이면 새로 만들어주고 반환

                CreateDecadeYearDto createDecadeYearDto = CreateDecadeYearDto.builder()
                        .childCenter(fetchedChildCenter)
                        .decadeStartYear(requestDecadeData.getDecadeStartYear())
                        .build();

                decadeYear = decadeYearService.createDecadeYear(createDecadeYearDto);
            }

            CreateYearHistoryDto createYearHistoryDto = CreateYearHistoryDto.builder()
                    .decadeYear(decadeYear)
                    .yearList(requestDecadeData.getYearList())
                    .build();

            List<YearHistory> saveAllYear = yearHistoryService.saveAllYear(createYearHistoryDto);

            decadeYear.getYearList().addAll(saveAllYear);
        }

    }

    @Override
    public void updateYearHistory(List<RequestDecadeDataDto> yearDataList) {
        //클라이언트가 만약 구분해서 주면, decadeStartYear의 범위 내부에서,  yearHistoryImageIndex에 해당하는 필드를 가져와서, 내부함수 update 로 값을 바꾸면 끝임.
    }

}
