package com.github.ichtion.strongtypes

import com.github.ichtion.annotations.ValueObject

import static java.util.UUID.randomUUID

@ValueObject
class ReleaseId {
    def id

    static ReleaseId randomReleaseId() {
        new ReleaseId(id: randomUUID())
    }
}
