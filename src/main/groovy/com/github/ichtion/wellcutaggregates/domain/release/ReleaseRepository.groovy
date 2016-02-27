package com.github.ichtion.wellcutaggregates.domain.release

import com.github.ichtion.strongtypes.ReleaseId

interface ReleaseRepository {
    Release getRelease(ReleaseId releaseId)
    void saveRelease(Release release)
}