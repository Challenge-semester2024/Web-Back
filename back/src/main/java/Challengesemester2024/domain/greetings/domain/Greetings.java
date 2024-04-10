package Challengesemester2024.domain.greetings.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "greetings")
public class Greetings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "greetings_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String pictureUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String memo;

    public void update(String memo){
        this.pictureUrl=pictureUrl;
        this.memo=memo;
    }

}
