package com.group.libraryapp.domain.book

import lombok.Builder
import javax.persistence.*

@Entity
class Book constructor(
    @Column(nullable = false)
    val name: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    val id: Long? = null,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다.")
        }
    }
}