package Challengesemester2024.domain.childCenter.model;

import Challengesemester2024.domain.manager.model.Manager;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "childCenter")
public class ChildCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childCenter_id;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNumId;

    @Column(nullable = false, length = 255)
    private String ceoName;

    @Column(nullable = false, unique = true, length = 255)
    private String centerName;

    @Column(nullable = false, unique = true, length = 255)
    private String roadAddress;

    @Column(nullable = false, unique = true, length = 255)
    private String detailAddress;

//아직 s3 쓸 줄 모릅니다 하하하하하하하
//    @Column(nullable = false, unique = true, length = 255)
//    private String certificate;

    public ChildCenter(String ceoName, String centerName, String phoneNumId, String roadAddress, String detailAddress) {
        this.childCenter_id=null;
        this.ceoName = ceoName;
        this.centerName = centerName;
        this.phoneNumId = phoneNumId;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

}
