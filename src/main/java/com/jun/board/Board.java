package com.jun.board;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Board {
    public Board() { }

    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private int boardIdx;

    private String title;
    private String contents;

    @Column(insertable = false)
    private int hitCnt;

    @Column(insertable = false, updatable = false)
    private String creatorId;

    @Column(insertable = false)
    private String createdDatetime;

    @Column(insertable = false)
    private String updaterId;

    @Column(insertable = false)
    private String updatedDatetime;
}
