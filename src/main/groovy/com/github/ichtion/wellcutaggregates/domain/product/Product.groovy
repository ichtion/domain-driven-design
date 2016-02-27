package com.github.ichtion.wellcutaggregates.domain.product

import com.github.ichtion.DomainException
import com.github.ichtion.annotations.AggregateRoot
import com.github.ichtion.annotations.Invariants
import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.strongtypes.ReleaseName
import com.github.ichtion.strongtypes.TenantId
import com.github.ichtion.wellcutaggregates.domain.release.Release

import static com.github.ichtion.strongtypes.ReleaseId.randomReleaseId

@AggregateRoot(
        identifiers = ['id'],
        invariants = [
                'you cannot add a RELEASE to a PRODUCT when End-Of-Life date passed',
                'you cannot have two RELEASES with the same name under the same product',
        ])
class Product {
    final ProductId id
    final TenantId tenantId
    private Date endOfLife
    private List<ReleaseName> releases

    Product(TenantId tenantId, Date endOfLife) {
        this.tenantId = tenantId
        this.endOfLife = endOfLife
    }

    @Invariants(['you cannot add a RELEASE to a PRODUCT when End-Of-Life date passed',
            'you cannot have two RELEASES with the same name under the same product'])
    Release scheduleRelease(Date releaseStartDate, Date releaseEndDate, ReleaseName releaseName) {
        if (releaseStartDate > releaseEndDate)
            throw new DomainException('startDate should be before endDate')
        if (releaseEndDate > endOfLife)
            throw new DomainException('endDate should be before product endOfLife')
        if (releases.contains(releaseName))
            throw new DomainException('release with such name already exists')

        releases.add(releaseName)

        new Release(id: randomReleaseId(),
                startDate: releaseStartDate,
                endDate: releaseEndDate,
                releaseName: releaseName,
                productId: id,
                tenantId: tenantId)
    }
}
