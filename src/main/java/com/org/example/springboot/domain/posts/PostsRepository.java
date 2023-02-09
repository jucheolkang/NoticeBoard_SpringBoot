package com.org.example.springboot.domain.posts;

import com.org.example.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    /*Posts 클래스로 Database를 접근하게 해줄 JpaRepository
JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD가 생성된다
Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
* 둘은 밀접한 관계이고, Entity 클래스는 기본 Repository는 없이는 제대로 역활을 할 수가 없다.*/
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

