package Challengesemester2024.domain.childCenter.controller;

import Challengesemester2024.Exception.collections.InputValid.BindingErrors;
import Challengesemester2024.domain.childCenter.dto.put.RequestFindChildCenterDto;
import Challengesemester2024.domain.childCenter.dto.put.ResponseChildCenterDto;
import Challengesemester2024.domain.childCenter.service.ChildCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/center")
public class ChildCenterController {
    private final ChildCenterService childCenterService;


    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseChildCenterDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/find") //인삿말 db 변경 -> 이미 회원가입때 만들어서 다 update라고 치면 됨
    public ResponseEntity<?> findChildCenter(@RequestBody @Valid RequestFindChildCenterDto requestFindChildCenterDto, BindingResult bindingResult) throws IOException {
        handleBindingErrors(bindingResult);

        List<ResponseChildCenterDto> response = childCenterService.findChildCenter(requestFindChildCenterDto);

        return ResponseEntity.ok(response);
    }


    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}
