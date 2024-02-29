package Challengesemester2024.domain.childCenter.model;

import Challengesemester2024.businessProcess.auth.dto.auth.S3urlDto;
import Challengesemester2024.domain.facilityIntroduction.model.FacilityIntroduction;
import Challengesemester2024.domain.greetings.domain.Greetings;
import Challengesemester2024.domain.routeInfo.domain.RouteInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "child_center")
public class ChildCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_center_id")
    private Long id;

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

    @Column(nullable = false, unique = true, length = 255)
    private String certificate;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "facility_introduction_id")
    private FacilityIntroduction facilityIntroduction;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "greetings_id")
    private Greetings greetings;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "route_info_id")
    private RouteInfo routeInfo;

    public ChildCenter(String ceoName, String centerName, String phoneNumId, String roadAddress,
                       String detailAddress, S3urlDto certificate,
                       FacilityIntroduction facilityIntroduction, Greetings greetings, RouteInfo routeInfo) {
        this.id = null;
        this.ceoName = ceoName;
        this.centerName = centerName;
        this.phoneNumId = phoneNumId;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.certificate = certificate.getS3url();
        this.facilityIntroduction = facilityIntroduction;
        this.greetings = greetings;
        this.routeInfo=routeInfo;
    }

}
