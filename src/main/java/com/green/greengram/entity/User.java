package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"uid"}
                )
        }
)

public class User extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = true, length = 30)
    private String userName;

    @Column(nullable = false, length = 50)
    private String uid;

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = true, length = 100)
    private String pic;

}
