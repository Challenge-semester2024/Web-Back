package Challengesemester2024.businessProcess.facade.controller;

import Challengesemester2024.businessProcess.facade.service.DatabaseFacadeService;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentDetailDto;
import Challengesemester2024.domain.childCenter.dto.get.ResponseChildCenterFacilityInfoDto;
import Challengesemester2024.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/get")
public class AppGetController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseChildCenterFacilityInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/{id}/facility/info")
    public ResponseEntity<?> getChildCenterFacilityIntroById(@PathVariable Long id) {
        ResponseChildCenterFacilityInfoDto responseDto = databaseFacadeService.getChildCenterFailcilityInfo(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RecruitmentSummaryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/{id}/recruitment/preview")
    public ResponseEntity<?> getChildCenterRecruitmentPreviewById(@PathVariable Long id) {
        List<RecruitmentSummaryDto> responseDto = databaseFacadeService.getChildCenterRecruitmentPreveiw(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/center/{id}/recruitment/detail")
    public ResponseEntity<?> getChildCenterRecruitmentDetailById(@PathVariable Long id) {
        RecruitmentDetailDto responseDto = databaseFacadeService.getRecruitmentDetail(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }





}
