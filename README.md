# domain-driven-design
Repository for DDD training

Invariants to be protected by defined aggregates:
- you cannot add a RELEASE to a PRODUCT when End-Of-Life date passed
- you cannot add a SPRINT to a shipped RELEASE
- you cannot add a STORY to a "Completed SPRINT"
- you cannot add a TASK to s STORY that is already "Done"
