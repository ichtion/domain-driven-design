package com.github.ichtion.largeaggregate

import com.github.ichtion.annotations.Entity
import groovy.transform.PackageScope

@Entity(identifiers = ['description', 'originalEstimate'])
@PackageScope
class Task {
    final String description
    final int originalEstimate
    int hoursToFinish

    Task(String description, int originalEstimate) {
        this.description = description
        this.originalEstimate = originalEstimate
        this.hoursToFinish = originalEstimate
    }
}
