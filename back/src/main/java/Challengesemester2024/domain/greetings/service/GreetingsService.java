package Challengesemester2024.domain.greetings.service;

import Challengesemester2024.Exception.collections.IoException.ImageInputException;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.greetings.dto.UpdateGreetingsDto;

import java.io.IOException;
import java.util.List;

public interface GreetingsService {
    Greetings createGreetings() throws IOException;

    void updateGreetings(UpdateGreetingsDto updateGreetingsDto) throws ImageInputException;
    List<Greetings> getAllGreetins();
}
