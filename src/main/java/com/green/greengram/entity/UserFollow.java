package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class UserFollow extends CreatedAt {

    @EmbeddedId
    private UserFollowIds userFollowIds;
    // 복합기 설정 // 따로 class 만들어서 복합기 만들어야함

    // 관계설정


    // 아래는 포린키 포린키 (FK)
    @MapsId("fromUserId") // 이거는 뒤에 ids의 필드명 적는거
    @ManyToOne
    @JoinColumn(name="from_user_id") // join 하고 컬럼명 보이는거
    private User fromUserId;

    @MapsId("toUserId")
    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toUserId;





//    @Id
//    @ManyToOne
//    @JoinColumn(name = "from_user_id")
//    private User fromUserId;
//
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "to_user_id")
//    private User toUserId;


}

