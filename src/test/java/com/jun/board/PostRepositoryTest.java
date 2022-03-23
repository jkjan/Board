package com.jun.board;

import com.jun.board.post.Post;
import com.jun.board.post.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        postRepository.deleteAll();
    }

    @Test
    void insertBoard() {
        postRepository.save(testEntity);

        Optional<Post> retrievedBd = postRepository.findById(1);

        assertTrue(
                retrievedBd.isPresent() &&
                retrievedBd.get().getTitle().equals(TEST_TITLE) &&
                retrievedBd.get().getContents().equals(TEST_CONTENTS));
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
        assertTrue(retrievedBd.isPresent() && retrievedBd.get().getHitCnt() == 1);
    }

    @Test
    void updateBoard() {
        postRepository.save(testEntity);
        testEntity.setTitle(MODIFIED_TITLE);
        testEntity.setContents(MODIFIED_CONTENTS);

        Optional<Post> retrievedBd = postRepository.findById(1);
        assertTrue(retrievedBd.isPresent() && retrievedBd.get().getTitle().equals(MODIFIED_TITLE) && retrievedBd.get().getContents().equals(MODIFIED_CONTENTS));
    }
}