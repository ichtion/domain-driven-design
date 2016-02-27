package com.github.ichtion.wellcutaggregates.infrastructure

import com.github.ichtion.strongtypes.ReleaseId
import com.github.ichtion.wellcutaggregates.domain.release.Release
import com.github.ichtion.wellcutaggregates.domain.release.ReleaseRepository

class ReleaseRepositoryImpl implements ReleaseRepository {

    @Override
    Release getRelease(ReleaseId releaseId) {
        return null
    }

    @Override
    void saveRelease(Release release) {

    }
}
