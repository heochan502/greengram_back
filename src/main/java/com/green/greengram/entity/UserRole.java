package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class UserRole
{

    @EmbeddedId
    private UserRoleIds userRoleIds;
    // 관계설정을 role에서만 해서 user만 저장한다고 다를게 안되고 따로
    // userrole을 데이터 입력해야 한다.
    @ManyToOne
    @MapsId("userId") // 관계설정할 필드명
    @JoinColumn(name="user_id")
    private User user;
}
