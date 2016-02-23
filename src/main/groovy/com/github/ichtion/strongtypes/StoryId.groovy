package com.github.ichtion.strongtypes

import com.github.ichtion.annotations.ValueObject
import groovy.transform.TupleConstructor

@ValueObject
@TupleConstructor
class StoryId {
    def id

    static StoryId randomId() {
        new StoryId(id: UUID.randomUUID().toString())
    }
}
