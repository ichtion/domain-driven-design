package com.github.ichtion.largeaggregate

import com.github.ichtion.DomainException
import com.github.ichtion.annotations.AggregateRoot
import com.github.ichtion.annotations.Invariant
import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.ReleaseName
import com.github.ichtion.strongtypes.SprintId
import com.github.ichtion.strongtypes.StoryId
import com.github.ichtion.strongtypes.TenantId

/**
 * We have two options here:
 * a) anemic-ish model - #@AggregateRoot protects all invariants and #@Entities are anemic
 *        - Product contains only releases (Demeter rule will be broken)
 *        - Product contains all entities (Demeter rule preserved)
 * b) we delegate protection of given invariant to an #@Entity
 * Such situation may indicate wrong boundaries of an aggregate
 */
@AggregateRoot(
        identifiers = ['id'],
        invariants = [
                'you cannot add a RELEASE to a PRODUCT when End-Of-Life date passed',
                'you cannot add a SPRINT to a shipped RELEASE',
                'you cannot add a STORY to a "Completed SPRINT"',
                'you cannot add a TASK to s STORY that is already "Done"'
        ])
class Product {
    final ProductId id
    final TenantId tenantId
    private Date endOfLife
    private Map<ReleaseName, Release> releases

    Product(TenantId tenantId, Date endOfLife) {
        this.tenantId = tenantId
        this.endOfLife = endOfLife
    }

    @Invariant('you cannot add a RELEASE to a PRODUCT when End-Of-Life date passed')
    void scheduleRelease(Date startDate, Date endDate, ReleaseName name) {
        if (startDate > endDate)
            throw new DomainException('startDate should be before endDate')
        if (endDate > endOfLife)
            throw new DomainException('endDate should be before product endOfLife')
        if (releases.keySet().contains(name))
            throw new DomainException('release with such name already exists')

        releases.put(name, new Release(startDate, endDate, name))
    }

    @Invariant('you cannot add a SPRINT to a shipped RELEASE')
    void scheduleSprint(Date startDate, Date endDate, ReleaseName releaseName) {
        if (!releases.keySet().contains(releaseName))
            throw new DomainException('specified release does not exist')

        Release release = releases.get(releaseName)

        if (endDate > release.shipDate)
            throw new DomainException('sprint endDate must be before release shipDate')

        release.scheduleSprint(new Sprint(startDate: startDate, endDate: endDate))
    }

    @Invariant('you cannot add a STORY to a "Completed SPRINT"')
    void addStoryToSprint(String storyDescription, SprintId sprintId,
                          ReleaseName releaseName) {
        Sprint sprint = releases.get(releaseName).sprints.get(sprintId)

        if (sprint.completed()) {
            throw new DomainException('you cannot add a story to a completed sprint')
        }

        sprint.add(new Story(storyDescription: storyDescription))
    }

    @Invariant('you cannot add a TASK to s STORY that is already "Done"')
    void addTaskToStory(ReleaseName releaseName,
                        SprintId sprintId,
                        StoryId storyId,
                        String taskDescription,
                        int hoursToFinishTask) {
        //appropriate guards should be added here
        def story = releases.get(releaseName).sprints.get(sprintId).stories.get(storyId)

        if (story.isDone()) {
            throw new DomainException('you cannot add a TASK to a "Done" STORY')
        }

        story.addTask(new Task(taskDescription, hoursToFinishTask))

    }

    void markStoryAsDone(ReleaseName releaseName, SprintId sprintId, StoryId storyId) {
        //appropriate guards should be added here
        releases.get(releaseName).sprints.get(sprintId).stories.get(storyId).markStoryAsDone()
    }
}
