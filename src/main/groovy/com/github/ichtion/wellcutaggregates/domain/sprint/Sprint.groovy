package com.github.ichtion.wellcutaggregates.domain.sprint

import com.github.ichtion.DomainException
import com.github.ichtion.annotations.AggregateRoot
import com.github.ichtion.annotations.Invariant
import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.ReleaseId
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.TenantId
import com.github.ichtion.wellcutaggregates.domain.TimeService
import com.github.ichtion.wellcutaggregates.domain.userstory.UserStory
import groovy.transform.TupleConstructor

import static com.github.ichtion.wellcutaggregates.domain.sprint.CommittedUserStory.userStoryCommittedToSprint

@TupleConstructor
@AggregateRoot(identifiers = ['sprintId'],
        invariants = ['you cannot commit a STORY to a "Completed SPRINT"'])
class Sprint {
    private TenantId tenantId
    private ProductId productId
    private ReleaseId releaseId
    private SprintId sprintId

    private Date startDate
    private Date endDate
    private Set<CommittedUserStory> committedUserStories
    private TimeService timeService

    @Invariant('you cannot commit a STORY to a "Completed SPRINT"')
    void commitUserStoryToSprint(UserStory userStory) {
        if (userStory.tenantId != tenantId)
            throw new DomainException("user story must be defined in scope of sprint's tenant")
        if (userStory.productId != productId)
            throw new DomainException("user story must be defined in scope of sprint's product")
        if (isCompleted()) {
            throw new DomainException('you cannot commit a story to a completed sprint')
        }

        committedUserStories.add(userStoryCommittedToSprint(sprintId, userStory))
        userStory.markAsCommittedToSprint(sprintId)
    }

    boolean isCompleted() {
        timeService.now() > endDate
    }

    void uncommit(UserStory userStory) {
        committedUserStories.remove(userStoryCommittedToSprint(sprintId, userStory))
    }
}
