package com.github.ichtion.largeaggregate

import spock.lang.Specification

class StoryTest extends Specification {
    def 'default state of a story is CREATED'() {
        expect:
        Story story = new Story()
        story.storyState == Story.State.CREATED
    }

    def 'should be possible to overwrite story state in constructor'() {
        expect:
        Story story = new Story(storyState: Story.State.DONE)
        story.storyState == Story.State.DONE
    }

}
