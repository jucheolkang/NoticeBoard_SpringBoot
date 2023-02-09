package com.org.example.springboot.service.posts;

import com.org.example.springboot.domain.posts.Posts;
import com.org.example.springboot.domain.posts.PostsRepository;
import com.org.example.springboot.web.dto.PostsListResponseDto;
import com.org.example.springboot.web.dto.PostsResponseDto;
import com.org.example.springboot.web.dto.PostsSaveRequestDto;

import com.org.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. .id = "+id));
        posts.updata(requestDto.getTitle(),requestDto.getContent());
        return id;
         /*
        JPA의 엔티티 메니저가 활성화된 상태로(Spring Data Jpa를 쓴다면 - 기본 옵션) 트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
        이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
        즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다(더티 체킹)*/
    }
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());

    }
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);

    }
}
