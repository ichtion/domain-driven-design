package com.github.ichtion.strongtypes

import com.github.ichtion.annotations.ValueObject

@ValueObject
class UserStoryId {
    def id

    static randomUserStoryId() {
        new UserStoryId(id: UUID.randomUUID())
    }
}
