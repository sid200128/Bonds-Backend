package com.bonds.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String bookName;

    @OneToMany(mappedBy = "book")
    private List<Trade> tradeList;

    @ManyToMany
    @JoinTable(
            name = "book_user",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> userList;
}
