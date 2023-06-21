package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

class CalculateJunitTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {

        }

        @AfterAll
        @JvmStatic
        fun afterAll() {

        }
    }

    @BeforeEach
    fun beforeEach() {
        println("테스트 시작")
    }

    @AfterEach
    fun afterEach() {
        println("테스트 종료 ")
    }

    @Test
    @DisplayName("더하기")
    fun addTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        assertThat(calculator.number).isEqualTo(8)
    }
    
    @Test
    @DisplayName("")        
    fun minus() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    @DisplayName("곱하기")
    fun multiply() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.multiply(3)

        //then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    @DisplayName("나누기 성공")
    fun divide() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.divide(3)

        //then
        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    @DisplayName("나누기 성공")
    fun divideException() {
        //given
        val calculator = Calculator(5)

        //when & then
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        }
    }
}