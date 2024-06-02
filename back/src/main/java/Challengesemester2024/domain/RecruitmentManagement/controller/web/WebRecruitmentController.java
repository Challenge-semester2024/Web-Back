package Challengesemester2024.domain.RecruitmentManagement.controller.web;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.config.constant.ControllerConstants;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentPageDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RequestRecruitmentDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitmentAccept.dto.VolunteerAcceptanceDto;
import Challengesemester2024.domain.RecruitmentManagement.facade.RecruitmentFacadeService;
import Challengesemester2024.domain.volunteer.dto.VolunteerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/web/recruitment")
public class WebRecruitmentController {
    private final RecruitmentFacadeService recruitmentFacadeService;

    @Transactional
    @Operation(summary = "createRecruitment", description = "봉사공고 등록 Api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "봉사공고 업로드가 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/create")
    public ResponseEntity<?> createRecruitment(@RequestBody @Valid RequestRecruitmentDto requestRecruitmentDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        recruitmentFacadeService.createRecruitment(requestRecruitmentDto);
        return new ResponseEntity<>(ControllerConstants.completecreateRecruitment, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "Get Recruitments Pagination", description = "봉사공고 목록 페이지네이션")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecruitmentPageDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/get/pagination")
    public RecruitmentPageDto getRecruitments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recruitmentFacadeService.getRecruitments(pageable);
    }

    @GetMapping("/get/waiting")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getVolunteersByDate(@RequestParam LocalDate date) {
        List<VolunteerResponseDto> volunteers = recruitmentFacadeService.getVolunteersByDate(date);
        return new ResponseEntity<>(volunteers, HttpStatus.OK);
    }


    @PostMapping("/accept")
    @Transactional
    public ResponseEntity<String> acceptVolunteer(@RequestBody VolunteerAcceptanceDto volunteerAcceptanceDto) {
        try {
            recruitmentFacadeService.acceptVolunteer(volunteerAcceptanceDto.getRecruitmentId(), volunteerAcceptanceDto.getVolunteerId(), volunteerAcceptanceDto.getRecruitmentDate());
            return new ResponseEntity<>("봉사공고 신청이 승인되었습니다.", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
