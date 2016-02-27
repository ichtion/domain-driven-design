package com.github.ichtion.wellcutaggregates.infrastructure

import com.github.ichtion.strongtypes.UserStoryId
import com.github.ichtion.wellcutaggregates.domain.userstory.UserStory
import com.github.ichtion.wellcutaggregates.domain.userstory.UserStoryRepository

class UserStoryRepositoryImpl implements UserStoryRepository {
    @Override
    UserStory getUserStory(UserStoryId id) {
        return null
    }

    @Override
    void saveUserStory(UserStory story) {

    }
}
