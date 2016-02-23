package com.github.ichtion.largeaggregate

import com.github.ichtion.annotations.Entity
import com.github.ichtion.strongtypes.StoryId
import groovy.transform.PackageScope
import groovy.transform.TupleConstructor

import static com.github.ichtion.largeaggregate.Story.State.CREATED
import static com.github.ichtion.largeaggregate.Story.State.DONE

@Entity(identifiers = ['id'])
@PackageScope
@TupleConstructor
class Story {
    StoryId id = StoryId.randomId()
    String description
    State storyState = CREATED
    List<Task> tasks

    void addTask(Task task) {
        tasks.add(task)
    }

    boolean isDone() {
        storyState == DONE
    }

    enum State {
        CREATED, ANALYZED, IN_DEVELOPMENT, DONE
    }

    void markStoryAsDone() {
        storyState = DONE
    }
}
