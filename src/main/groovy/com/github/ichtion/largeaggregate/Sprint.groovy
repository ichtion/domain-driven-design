package com.github.ichtion.largeaggregate

import com.github.ichtion.annotations.Entity
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.StoryId
import groovy.transform.PackageScope
import groovy.transform.TupleConstructor

@Entity(identifiers = ['id'])
@PackageScope
@TupleConstructor
class Sprint {
    SprintId id
    Date startDate
    Date endDate
    Map<StoryId, Story> stories

    boolean completed() {
        now() > endDate
    }

    private static Date now() {
        new Date()
    }

    void add(Story story) {
        stories.put(story.id, story)
    }
}
