package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FeedComment extends UpdatedAt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedCommentId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feedId;

    @Column(length = 150, nullable = false)
    private String comment;
}
