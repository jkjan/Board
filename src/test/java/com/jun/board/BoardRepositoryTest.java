package com.jun.board;

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
class BoardRepositoryTest {
    final String TEST_TITLE = "test_title";
    final String TEST_CONTENTS = "test_contents";
    final String MODIFIED_TITLE = "modified_title";
    final String MODIFIED_CONTENTS = "modified_contents";

    Board testEntity;

    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.testEntity = new Board(TEST_TITLE, TEST_CONTENTS);
        entityManager.createNativeQuery("ALTER TABLE t_board AUTO_INCREMENT = 1").executeUpdate();
    }

    @AfterEach
    void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    void insertBoard() {
        boardRepository.save(testEntity);

        Optional<Board> retrievedBd = boardRepository.findById(1);

        assertTrue(
                retrievedBd.isPresent() &&
                retrievedBd.get().getTitle().equals(TEST_TITLE) &&
                retrievedBd.get().getContents().equals(TEST_CONTENTS));
    }

    @Test
    void deleteBoard() {
        boardRepository.save(testEntity);
        boardRepository.deleteById(1);
        Optional<Board> retrievedBd = boardRepository.findById(1);
        assertTrue(retrievedBd.isEmpty());
    }

    @Test
    void hitBoard() {
        boardRepository.save(testEntity);
        testEntity.setHitCnt(testEntity.getHitCnt() + 1);

        Optional<Board> retrievedBd = boardRepository.findById(1);
        assertTrue(retrievedBd.isPresent() && retrievedBd.get().getHitCnt() == 1);
    }

    @Test
    void updateBoard() {
        boardRepository.save(testEntity);
        testEntity.setTitle(MODIFIED_TITLE);
        testEntity.setContents(MODIFIED_CONTENTS);

        Optional<Board> retrievedBd = boardRepository.findById(1);
        assertTrue(retrievedBd.isPresent() && retrievedBd.get().getTitle().equals(MODIFIED_TITLE) && retrievedBd.get().getContents().equals(MODIFIED_CONTENTS));
    }
}