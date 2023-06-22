package com.group.libraryapp.service

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
){
    @Test
    @DisplayName("유저 저장이 정상 동작 한다.")
    fun saveUserTest() {
        //given
        val name = "gosekose"
        val request = UserCreateRequest(name, null)

        //when
        userService.saveUser(request)

        //then
        val result = userRepository.findByName(name)
            .orElseThrow()
        assertThat(result.name).isEqualTo(name)
        assertThat(result.age).isNull()
    }

    @Test
    @DisplayName("유저 조회가 정상 동작 한다.")
    fun getUserTest() {
        //given
        userRepository.saveAll(listOf(User("A", 20), User("B", 30), User("C", null)))

        //when
        val results = userService.users

        //then
        assertThat(results).hasSize(3)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B", "C")
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20, 30, null)
    }
    
    @Test
    @DisplayName("유저 업데이트가 정상 동작 한다.")
    fun updateUserNameTest() {
        //given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id, "B")

        //when
        userService.updateUserName(request)

        //then
        val result = userRepository.findByName(request.name).orElseThrow()
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작 한다.")
    fun deleteUserTest() {
        //given
        val name = "A"
        userRepository.save(User(name, null))

        //when
        userService.deleteUser(name)

        //then
        assertThat(userRepository.findByName(name)).isEmpty
    }
}