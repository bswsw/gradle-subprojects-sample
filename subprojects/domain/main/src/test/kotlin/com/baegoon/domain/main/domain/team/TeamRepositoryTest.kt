package com.baegoon.domain.main.domain.team

import com.baegoon.domain.main.config.MainJpaConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.EntityNotFoundException

@RunWith(SpringRunner::class)
@DataJpaTest
@Import(MainJpaConfig::class)
@ActiveProfiles("domain-main-test")
class TeamRepositoryTest {

    @Autowired
    private lateinit var teamRepository: TeamRepository

    @Test
    fun `이름 검색 테스트`() {
        val team = this.teamRepository.save(Team("테스트팀"))

        val foundTeam = this.teamRepository.findByName(team.name)
            ?: throw EntityNotFoundException()

        assertThat(foundTeam.name).isEqualTo(team.name)
    }
}
