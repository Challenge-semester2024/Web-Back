package Challengesemester2024.domain.facility.floorPicutre.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFloorPictureDto {
    @NotBlank
    private String floor;

    @NotBlank
    private String purpose;
}
