package com.github.ichtion.wellcutaggregates.application

import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.ReleaseId
import com.github.ichtion.strongtypes.ReleaseName
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.UserStoryId
import com.github.ichtion.wellcutaggregates.domain.product.ProductRepository
import com.github.ichtion.wellcutaggregates.domain.release.Release
import com.github.ichtion.wellcutaggregates.domain.release.ReleaseRepository
import com.github.ichtion.wellcutaggregates.domain.sprint.SprintRepository
import com.github.ichtion.wellcutaggregates.domain.userstory.UserStoryRepository
import org.springframework.transaction.annotation.Transactional

class ApplicationService {
    private final ProductRepository productRepository
    private final ReleaseRepository releaseRepository
    private final SprintRepository sprintRepository
    private final UserStoryRepository userStoryRepository

    ApplicationService(ProductRepository productRepository,
                       ReleaseRepository releaseRepository,
                       SprintRepository sprintRepository,
                       UserStoryRepository userStoryRepository) {
        this.productRepository = productRepository
        this.releaseRepository = releaseRepository
        this.sprintRepository = sprintRepository
        this.userStoryRepository = userStoryRepository
    }

    @Transactional
    void scheduleRelease(ProductId productId,
                         Date releaseStartDate,
                         Date releaseEndDate,
                         ReleaseName releaseName) {
        def product = productRepository.getProduct(productId)
        Release release = product.scheduleRelease(releaseStartDate, releaseEndDate, releaseName)
        releaseRepository.saveRelease(release)
    }

    @Transactional
    void scheduleSprint(ReleaseId releaseId,
                        Date sprintStartDate,
                        Date sprintEndDate) {
        def release = releaseRepository.getRelease(releaseId)
        def sprint = release.scheduleSprint(sprintStartDate, sprintEndDate)
        sprintRepository.saveSprint(sprint)
    }

    @Transactional
    void commitUserStoryToSprint(UserStoryId storyId, SprintId sprintId) {
        def userStory = userStoryRepository.getUserStory(storyId)
        def sprint = sprintRepository.getSprint(sprintId)

        if (userStory.isCommittedToSprint()) {
            def sprintContainingStory = sprintRepository.getSprint(userStory.sprintId)
            sprintContainingStory.uncommit(userStory)
        }

        sprint.commitUserStoryToSprint(userStory)
    }
}
