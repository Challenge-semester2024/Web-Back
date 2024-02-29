package Challengesemester2024.domain.manager.model;

import Challengesemester2024.domain.childCenter.model.ChildCenter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String emailId;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ManagerRoleEnum role;

    @OneToOne(cascade = CascadeType.REMOVE) //해당 db삭제시, 연결된 db 모두 삭제됨
    @JoinColumn(name = "child_center_id")
    private ChildCenter childCenter;

    private Manager(String email, String password, String phoneNum, ChildCenter childCenter, ManagerRoleEnum role) {
        this.id = null; //jpa가 알아서 관리해주기 때문에 null
        this.emailId = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.childCenter = childCenter;
        this.role = role;
    }

    public Manager(String email, String password, String phoneNum, ChildCenter childCenter) {
        this(email, password, phoneNum, childCenter, ManagerRoleEnum.User);
    }

    public enum ManagerRoleEnum {
        User, ADMIN
    }

}
