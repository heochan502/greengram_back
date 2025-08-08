package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @ManyToOne
    @JoinColumn(name ="writer_user_id" ,nullable = false )
    private User writerUserId;

    @Column (length = 30)
    private String location;

    @Column (length = 1_000)
    private String contents;

    //양방향 관계 설정
    @Builder.Default // builder패턴사용시 null 되는데 이 애노테이션을 주면 주소생성됨
    @OneToMany(mappedBy = "feed",  cascade = CascadeType.ALL, orphanRemoval = true)
    // 최소 1개 라는 의미로 1 넣음
    private List<FeedPic> feedPicList = new ArrayList<>(1);

    public void addFeedPics(List<String> picFileNames) {
        for(String picFileName : picFileNames) {
            FeedPicIds feedPicIds = FeedPicIds.builder()
                    .feedId(this.feedId)
                    .pic(picFileName)
                    .build();
            FeedPic feedPic = FeedPic.builder()
                    .feedPicIds(feedPicIds)
                    .feed(this)
                    .build();
            this.feedPicList.add(feedPic);
        }
    }

}
