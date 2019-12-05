package com.baegoon.domain.main.domain.team

import com.baegoon.domain.main.config.MainJpaConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import javax.persistence.EntityNotFoundException

@DataJpaTest
@Import(MainJpaConfig::class)
@ActiveProfiles("domain-main-test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class TeamRepositoryTest(
    private val teamRepository: TeamRepository
) {

    @Test
    @DisplayName("이름 검색 테스트")
    fun `이름 검색 테스트`() {
        val team = this.teamRepository.save(Team("테스트팀"))

        val foundTeam = this.teamRepository.findByName(team.name)
            ?: throw EntityNotFoundException()

        assertThat(foundTeam.name).isEqualTo(team.name)
    }
}
