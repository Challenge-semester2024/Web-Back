package Challengesemester2024.domain.recruitment.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.recruitment.facade.RecruitmentFacadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/recruitment")
public class RecruitmentController {
    private final RecruitmentFacadeService recruitmentFacadeService;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> createRecruitment(@RequestBody @Valid RequestRecruitmentDto requestRecruitmentDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        recruitmentFacadeService.createRecruitment(requestRecruitmentDto);
        return new ResponseEntity<>(ControllerConstants.completecreateRecruitment, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/get")
    public RecruitmentPageDto getRecruitments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recruitmentFacadeService.getRecruitments(pageable);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
