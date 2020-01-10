package com.baegoon.app.api.controller

import com.baegoon.domain.main.domain.item.laptop.Laptop
import com.baegoon.domain.main.domain.item.laptop.LaptopRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.requestParameters
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(HelloController::class)
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class HelloControllerTest(
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var laptopRepository: LaptopRepository

    @Test
    @DisplayName("GET /hello 테스트")
    fun hello() {
        // given
        val laptop = Laptop(
            name = "맥북",
            price = 1500000,
            ports = 4,
            screenSize = 15
        )

        given(laptopRepository.save(laptop))
            .willReturn(laptop)

        // when & then
        mockMvc.perform(get("/hello").param("name", laptop.name))
            .andExpect(status().isOk)
            .andDo(print())
            .andDo(
                document(
                    "hello",
                    requestParameters(
                        parameterWithName("name").description("이름")
                    )
                )
            )
    }
}
