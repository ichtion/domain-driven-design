package com.github.ichtion.wellcutaggregates.domain.userstory

import com.github.ichtion.strongtypes.UserStoryId

interface UserStoryRepository {
    UserStory getUserStory(UserStoryId id)
    void saveUserStory(UserStory story)
}
