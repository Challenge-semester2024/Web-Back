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
    private Long manager_id;

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
    @JoinColumn(name = "childCenter_id")
    private ChildCenter childCenter_id;

    public Manager(String email, String password, String phoneNum, ManagerRoleEnum role) {
        this.manager_id = null; //jpa가 알아서 관리해주기 때문에 null
        this.emailId = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.role = role;
    }
    //
    public Manager(String email, String password, String phoneNum) {
        this(email, password, phoneNum, ManagerRoleEnum.User);
    }

    public enum ManagerRoleEnum {
        User, ADMIN
    }

    //왜 저기 id는 null인가? 그리고 매핑 어디서 해주는거야?

}
