package Challengesemester2024.domain.yearHistory.service.decadeYear;

import Challengesemester2024.domain.center.childCenter.model.ChildCenter;
import Challengesemester2024.domain.yearHistory.dto.decadeYear.CreateDecadeYearDto;
import Challengesemester2024.domain.yearHistory.model.DecadeYear;
import Challengesemester2024.domain.yearHistory.repositroy.decadeYear.DecadeYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecadeYearServiceImpl implements DecadeYearService {
    private final DecadeYearRepository decadeYearRepository;

    @Override
    public DecadeYear createDecadeYear(CreateDecadeYearDto createDecadeYearDto) {
        DecadeYear decadeYear = DecadeYear.builder()
                .decadeStartYear(createDecadeYearDto.getDecadeStartYear())
                .yearList(new ArrayList<>())
                .childCenter(createDecadeYearDto.getChildCenter())
                .build();

        decadeYearRepository.save(decadeYear);

        return decadeYear;
    }

    @Override
    public List<DecadeYear> findAllDecadeYearDesc(ChildCenter fechedChildCenter) {
        //현재 보육원에 해당하는 db객체 찾기
        return decadeYearRepository.findByChildCenterOrderByDecadeStartYearAsc(fechedChildCenter);
    }
}
