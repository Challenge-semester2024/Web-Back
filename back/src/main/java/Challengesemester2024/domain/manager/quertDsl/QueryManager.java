package Challengesemester2024.domain.manager.quertDsl;

import Challengesemester2024.domain.manager.model.Manager;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

//EntityPathBase는 QueryDSL에서 사용하는 기본 클래스로, 엔티티에 대한 QueryDSL Query 타입을 생성하기 위한 메타 모델 클래스를 정의하는 데 사용된다.
public class QueryManager extends EntityPathBase<Manager> {
    //Manager 필드에 대한 QueryDSL Query 타입을 정의한다.
    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath emailId = createString("emailId");
    public final StringPath password = createString("password");
    public final StringPath phoneNum = createString("phoneNum");

    //string으로 생성자 형성
    public QueryManager(String variable) {
        super(Manager.class, variable);
    }
    //Manager.class 이 QueryDSL Query 타입이 어떤 엔티티를 대상으로 하는지를 나타내며, variable은 QueryDSL에서 사용될 변수명을 지정한다.


}
