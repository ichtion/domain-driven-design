package com.github.ichtion.annotations

@interface AggregateRoot {
    String[] identifiers()
    String[] invariants()
}