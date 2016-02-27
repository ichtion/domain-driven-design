package com.github.ichtion.strongtypes

import com.github.ichtion.annotations.ValueObject

@ValueObject
enum UserStoryEstimate {
    ONE(1), TWO(2), THREE(3), FIVE(5), EIGHT(8), THIRTEEN(13), TWENTY(20)

    private final int value

    UserStoryEstimate(int value) {
        this.value = value
    }
}
