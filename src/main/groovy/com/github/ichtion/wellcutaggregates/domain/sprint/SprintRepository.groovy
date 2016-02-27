package com.github.ichtion.wellcutaggregates.domain.sprint

import com.github.ichtion.strongtypes.SprintId

interface SprintRepository {
    Sprint getSprint(SprintId sprintId)
    void saveSprint(Sprint sprint)
}
