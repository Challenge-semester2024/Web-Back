package Challengesemester2024.domain.yearHistory.service.decadeYear;

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
    public List<DecadeYear> findAllDecadeYearDesc() {
        return decadeYearRepository.findAllByOrderByDecadeStartYearAsc();
    }
}
