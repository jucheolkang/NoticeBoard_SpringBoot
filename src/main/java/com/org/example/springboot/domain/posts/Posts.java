package com.org.example.springboot.domain.posts;

import com.org.example.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    /*@Setter가 없는 대신 생성자를 이용해서 최종 값을 db에 삽입,
     값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출 하는 것을 전제로 함
     지금은 생성자 대신 @Builder를 사용*/
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void updata(String title,String content){
        this.title = title;
        this.content = content;
    }

}


