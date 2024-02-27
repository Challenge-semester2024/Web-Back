package Challengesemester2024.domain.greetings.domain;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "greetings")
public class Greetings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "greetings_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String pictureUrl;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String memo;

    public Greetings(S3urlDto s3urlDto, String memo) {
        this.id = null;
        this.pictureUrl = s3urlDto.getS3url();
        this.memo = memo;
    }
}
