package Challengesemester2024.domain.yearHistory.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.yearHistory.dto.decadeYear.RequestDecadeDataDto;
import Challengesemester2024.domain.yearHistory.facade.YearFacadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/history")
public class yearHistoryController {
    private final YearFacadeService yearFacadeService;

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<?> updateYearHistory(@RequestBody @Valid List<RequestDecadeDataDto> yearDataList , BindingResult bindingResult){
        handleBindingErrors(bindingResult);
        yearFacadeService.createYearHistory(yearDataList);

        return new ResponseEntity<>(ControllerConstants.completeUpdateYearHistory, HttpStatus.OK);
    }


    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}
