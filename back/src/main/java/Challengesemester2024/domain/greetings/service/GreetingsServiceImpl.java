package Challengesemester2024.domain.greetings.service;

import Challengesemester2024.Exception.collections.IoException.NotExitsInitImageFile;
import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.businessProcess.util.UtilService;
import Challengesemester2024.config.DbInitConstants;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.greetings.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GreetingsServiceImpl implements GreetingsService{
    private final GreetingsRepository greetingsRepository;
    private final UtilService utilService;
    @Override
    public Greetings createGreetings() throws NotExitsInitImageFile {
        S3urlDto s3urlDto = getInitImageUrl();
        Greetings greetings = new Greetings(s3urlDto, DbInitConstants.greetingsInitMessage);
        try {
            greetingsRepository.save(greetings);
        } catch (DataIntegrityViolationException e) {
            // 유니크 키 겹쳤을 때 에러 발생
            throw new DuplicateUniqueKeyException();
        }

        return greetings;
    }

    private S3urlDto getInitImageUrl() throws NotExitsInitImageFile {
        String initImageUrl = null;

        try{
            initImageUrl = utilService.getInitImagePath(DbInitConstants.initImageFileName);
        } catch (IOException e) {
            throw new NotExitsInitImageFile();
        }

        S3urlDto s3urlDto = S3urlDto.builder()
                .S3url(initImageUrl)
                .build();

        return s3urlDto;
    }
}
