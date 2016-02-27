package com.github.ichtion.wellcutaggregates.domain.sprint

import com.github.ichtion.annotations.ValueObject
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.UserStoryEstimate
import com.github.ichtion.strongtypes.UserStoryId
import com.github.ichtion.wellcutaggregates.domain.userstory.UserStory
import groovy.transform.PackageScope

@PackageScope
@ValueObject
class CommittedUserStory {
    private SprintId sprintId
    private UserStoryId userStoryId
    private UserStoryEstimate estimate

    static CommittedUserStory userStoryCommittedToSprint(SprintId sprintId, UserStory userStory) {
        new CommittedUserStory(sprintId: sprintId, userStoryId: userStory.id, estimate: userStory.estimate)
    }
}
