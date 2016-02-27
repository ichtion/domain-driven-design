package com.github.ichtion.wellcutaggregates.domain.release

import com.github.ichtion.DomainException
import com.github.ichtion.annotations.AggregateRoot
import com.github.ichtion.annotations.Invariants
import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.ReleaseId
import com.github.ichtion.strongtypes.ReleaseName
import com.github.ichtion.strongtypes.TenantId
import com.github.ichtion.wellcutaggregates.domain.sprint.Sprint
import groovy.transform.TupleConstructor

import static com.github.ichtion.strongtypes.SprintId.randomSprintId

@TupleConstructor
@AggregateRoot(
        identifiers = ['releaseName'],
        invariants = ['you cannot add a SPRINT to a shipped RELEASE',
                'SPRINT cannot start before startDate of RELEASE',
                'SPRINT cannot end after endDate of RELEASE'
        ]
)
class Release {
    private TenantId tenantId
    private ProductId productId

    private ReleaseId id
    private ReleaseName releaseName
    private Date startDate
    private Date endDate

    @Invariants(['you cannot add a SPRINT to a shipped RELEASE',
            'SPRINT cannot start before startDate of RELEASE',
            'SPRINT cannot end after endDate of RELEASE'])
    Sprint scheduleSprint(Date sprintStartDate, Date sprintEndDate) {
        if (isShipped())
            throw new DomainException('Cannot add a sprint to already shipped release')
        if (sprintEndDate > endDate)
            throw new DomainException('sprint endDate must be before release endDate')
        if (sprintStartDate < startDate)
            throw new DomainException('sprint startDate must be after release startDate')

        new Sprint(tenantId: tenantId,
                productId: productId,
                releaseId: id,
                sprintId: randomSprintId(),
                startDate: sprintStartDate,
                endDate: sprintEndDate)
    }

    boolean isShipped() {
        now() > endDate
    }

    private static Date now() {
        new Date()
    }
}
