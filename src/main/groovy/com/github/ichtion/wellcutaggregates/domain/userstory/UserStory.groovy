package com.github.ichtion.wellcutaggregates.domain.userstory

import com.github.ichtion.annotations.AggregateRoot
import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.TenantId
import com.github.ichtion.strongtypes.UserStoryDescription
import com.github.ichtion.strongtypes.UserStoryEstimate
import com.github.ichtion.strongtypes.UserStoryId
import groovy.transform.TupleConstructor

@TupleConstructor
@AggregateRoot(identifiers = ['userStoryId'],
        invariants = [''])
class UserStory {
    private TenantId tenantId
    private ProductId productId
    private SprintId sprintId

    private UserStoryId id
    private UserStoryDescription description
    private UserStoryEstimate estimate

    void markAsCommittedToSprint(SprintId sprintId) {
        this.sprintId = sprintId
    }

    boolean isCommittedToSprint() {
        sprintId != null
    }
}
