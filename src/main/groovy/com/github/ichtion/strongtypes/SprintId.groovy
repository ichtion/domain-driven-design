package com.github.ichtion.strongtypes

import com.github.ichtion.annotations.ValueObject

import static java.util.UUID.randomUUID

@ValueObject
class SprintId {
    def id

    static SprintId randomSprintId() {
        new SprintId(id: randomUUID())
    }
}
