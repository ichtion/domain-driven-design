package com.github.ichtion.largeaggregate

import com.github.ichtion.annotations.Entity
import com.github.ichtion.strongtypes.ReleaseName
import com.github.ichtion.strongtypes.SprintId
import groovy.transform.PackageScope

@Entity(identifiers = ['releaseName'])
@PackageScope
class Release {
    ReleaseName releaseName
    Date startDate
    Date shipDate
    Map<SprintId, Sprint> sprints

    Release(Date startDate, Date shipDate, ReleaseName releaseName) {
        this.startDate = startDate
        this.shipDate = shipDate
        this.releaseName = releaseName
    }

    void scheduleSprint(Sprint sprint) {
        sprints.put(sprint.id, sprint)
    }


}
