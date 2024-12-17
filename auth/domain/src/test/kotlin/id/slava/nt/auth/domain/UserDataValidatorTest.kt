package id.slava.nt.auth.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class UserDataValidatorTest{

    private lateinit var userDataValidator: UserDataValidator

    @BeforeEach
    fun setUp(){
        userDataValidator = UserDataValidator(
            object : PatternValidator {
                override fun matches(value: String): Boolean {
                    return true
                }
            }

        )
    }


    @ParameterizedTest
    @CsvSource(
        "Test, false",
        "Password123, true",
        "password123, false",
        "PASSWORD123, false"
    )
    fun testValidPassword(password: String, expectedResult: Boolean){

        val result = userDataValidator.validatePassword(password)

        assertThat(result.isValidPassword).isEqualTo(expectedResult)

    }

}