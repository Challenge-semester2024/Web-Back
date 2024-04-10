package Challengesemester2024.domain.yearHistory.service.year;

import Challengesemester2024.domain.yearHistory.dto.yearHistory.CreateYearHistoryDto;
import Challengesemester2024.domain.yearHistory.dto.yearHistory.RequestYearDataDto;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import Challengesemester2024.domain.yearHistory.model.YearHistory;
import Challengesemester2024.domain.yearHistory.repositroy.year.YearHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YearHistoryServiceImpl implements YearHistoryService {
    private final YearHistoryRepository yearHistoryRepository;

    @Override
    public List<YearHistory> saveAllYear(CreateYearHistoryDto createYearHistoryDto) {
        List<YearHistory> yearList = new ArrayList<>();
        DecadeYear decadeYear = createYearHistoryDto.getDecadeYear();

        for(RequestYearDataDto yearData : createYearHistoryDto.getYearList()) {

            if( checkCreate(yearData) ){
                YearHistory yearHistory = YearHistory.builder()
                        .decadeYear(decadeYear)
                        .year(yearData.getYear())
                        .memo(yearData.getMemo())
                        .displayIndex(yearData.getDisplayIndex())
                        .build();

                yearList.add(yearHistory);
            }

        }
        //새롭게 추가되는 데이터의 경우에만 List에 담아 새롭게 저장.
        yearHistoryRepository.saveAll(yearList);

        return yearList;
    }

    private boolean checkCreate(RequestYearDataDto yearData){
        YearHistory yearHistory = yearHistoryRepository.findBydisplayIndex(yearData.getDisplayIndex());
        if(yearHistory==null) {
            return true;
        }
        updateYearHistory(yearData, yearHistory);
        return false;
    }

    private void updateYearHistory(RequestYearDataDto newYearHistory, YearHistory excitingYearHistory) {
        excitingYearHistory.update(newYearHistory.getYear(), newYearHistory.getMemo());
    }
}
