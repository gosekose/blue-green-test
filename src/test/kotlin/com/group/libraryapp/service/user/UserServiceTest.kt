package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
){

    @AfterEach
    fun clear() {
        userLoanHistoryRepository.deleteAll()
        userRepository.deleteAll()
    }

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
        assertThat(result!!.name).isEqualTo(name)
        assertThat(result.age).isNull()
    }

    @Test
    @DisplayName("유저 조회가 정상 동작 한다.")
    fun getUserTest() {
        //given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", 30),
            User("C", null)
        ))

        //when
        val results = userService.getUsers()

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
        val request = UserUpdateRequest(savedUser.id!!, "B")

        //when
        userService.updateUserName(request)

        //then
        val result = userRepository.findByName(request.name)
        assertThat(result!!.name).isEqualTo("B")
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
        assertThat(userRepository.findByName(name)).isNull()
    }
    
    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다.")
    fun getUserLoanHistoriesTest1() {
        //given
        userRepository.save(User("A", null))

        //when
        val results = userService.getUserLoanHistories()

        //then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작된다.")
    fun getUserLoanHistoriesTest2() {
        //given
        val savedUser = userRepository.save(User("A", null))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(savedUser, "object1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "object2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "object3", UserLoanStatus.RETURNED),
        ))

        //when
        val results = userService.getUserLoanHistories()

        //then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).hasSize(3)
        assertThat(results[0].books).extracting("name")
            .containsExactlyInAnyOrder("object1", "object2", "object3")
        assertThat(results[0].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true)
    }

    @Test
    @DisplayName("다수의 유저가 대출 기록을 조회한다.")
    fun getUserLoanHistoriesTest3() {
        //given
        val users = userRepository.saveAll(
            listOf(
                User("A", null),
                User("B", null)
            )
        )

        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(users[0], "object1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(users[0], "object2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(users[0], "object3", UserLoanStatus.RETURNED),

            UserLoanHistory.fixture(users[1], "object4", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(users[1], "object5", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(users[1], "object6", UserLoanStatus.RETURNED),
            UserLoanHistory.fixture(users[1], "object7", UserLoanStatus.RETURNED),
        ))

        //when
        val results = userService.getUserLoanHistories()

        //then
        assertThat(results).hasSize(2)

        assertThat(results[0].books).extracting("name")
            .containsExactlyInAnyOrder("object1", "object2", "object3")
        assertThat(results[0].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true)

        assertThat(results[1].books).extracting("name")
            .containsExactlyInAnyOrder("object4", "object5", "object6", "object7")
        assertThat(results[1].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true, true)
    }
}