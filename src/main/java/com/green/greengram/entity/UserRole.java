package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class UserRole
{

    @EmbeddedId
    private UserRoleIds userRoleIds;

    @ManyToOne
    @MapsId("userId") // 관계설정할 필드명
    @JoinColumn(name="user_id")
    private User user;
}
