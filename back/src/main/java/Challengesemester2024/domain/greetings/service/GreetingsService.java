package Challengesemester2024.domain.greetings.service;

import Challengesemester2024.domain.greetings.domain.Greetings;

import java.io.IOException;

public interface GreetingsService {
    Greetings createGreetings() throws IOException;
}
