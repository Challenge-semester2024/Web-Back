package Challengesemester2024.domain.greetings.dto;

import Challengesemester2024.domain.greetings.domain.Greetings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateGreetingsDto {
    Greetings oldGreeting;
    Greetings newGreeting;
}
