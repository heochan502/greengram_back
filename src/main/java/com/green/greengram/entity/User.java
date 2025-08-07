package com.green.greengram.entity;



import com.green.greengram.config.enumcode.model.EnumUserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
//@Table(
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"uid"}
//                )
//        }
//)
// 위는 복합적으로 줄 때 쓰면 댐

public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = true, length = 30)
    private String nickName;

    @Column(nullable = false, length = 50, unique = true)
    private String uid;

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = true, length = 100)
    private String pic;

    // 주 테이블 에서 mappedby 적고 컬럼명
    // cascade는 자식과 나랑 모든 연결 (내가 연속성 되면 자식도 영속성 되고, 내가 삭제 되면 자식도 삭제 된다. 등등
    // ohphanRemoval은 userRole에서 자식을 하나 제거함. 그러면 DB에도 뺀 자식은 삭제처리가 된다.
    // 부모가 없는 자식은 삭제를 한다
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>(1);

    public void addUserRole(List<EnumUserRole> enumUserRole) {

        for (EnumUserRole e : enumUserRole)
        {
            UserRoleIds ids = new UserRoleIds(this.userId, e);
            UserRole userRole = new UserRole(ids, this);

            this.userRoles.add(userRole);
        }

    }
}
