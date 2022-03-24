package com.jun.board;

import com.jun.board.post.Post;
import com.jun.board.post.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
    final String TEST_TITLE = "test_title";
    final String TEST_CONTENTS = "test_contents";
    final String MODIFIED_TITLE = "modified_title";
    final String MODIFIED_CONTENTS = "modified_contents";

    Post testEntity;

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.testEntity = new Post(TEST_TITLE, TEST_CONTENTS);
        entityManager.createNativeQuery("ALTER TABLE post AUTO_INCREMENT = 1").executeUpdate();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
        postRepository.deleteAll();
    }

    @Test
    void insertBoard() {
        postRepository.save(testEntity);
        entityManager.detach(testEntity);

        Optional<Post> retrievedBd = postRepository.findById(1);

        System.out.println(retrievedBd);
        assertTrue(retrievedBd.isPresent());
        assertEquals(TEST_TITLE, retrievedBd.get().getTitle());
        assertEquals(TEST_CONTENTS, retrievedBd.get().getContents());
        assertNotNull(retrievedBd.get().getCreatedDatetime());
    }

    @Test
    void deleteBoard() {
        postRepository.save(testEntity);
        postRepository.deleteById(1);
        Optional<Post> retrievedBd = postRepository.findById(1);
        assertTrue(retrievedBd.isEmpty());
    }

    @Test
    void hitBoard() {
        postRepository.save(testEntity);
        testEntity.setHitCnt(testEntity.getHitCnt() + 1);

        Optional<Post> retrievedBd = postRepository.findById(1);
        assertTrue(retrievedBd.isPresent());
        assertEquals(retrievedBd.get().getHitCnt(), 1);
    }

    @Test
    void updateBoard() {
        postRepository.save(testEntity);
        entityManager.detach(testEntity);

        testEntity.setTitle(MODIFIED_TITLE);
        testEntity.setContents(MODIFIED_CONTENTS);
        postRepository.updatePost(testEntity);

        Optional<Post> retrievedBd = postRepository.findById(1);
        assertTrue(retrievedBd.isPresent());
        assertEquals(retrievedBd.get().getTitle(), MODIFIED_TITLE);
        assertEquals(retrievedBd.get().getContents(), MODIFIED_CONTENTS);
    }
}