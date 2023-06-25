package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class LibraryAppApplication

fun main(args: Array<String>) {

    val applicationLocation: String = "spring.config.location=" +
            "classpath:application.yml," +
            "file:/home/koseyun/projects/spring/Kotlin_Spring_Library_Application/sources/prod-application.yml"
    SpringApplicationBuilder(LibraryAppApplication::class.java)
        .properties(applicationLocation)
        .run(*args)
}