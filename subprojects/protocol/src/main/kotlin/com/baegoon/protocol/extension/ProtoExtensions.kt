package com.baegoon.protocol.extension

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Timestamp.toLocalDate(): LocalDate {
    return Instant
        .ofEpochSecond(this.seconds, this.nanos.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

fun LocalDate.toTimestampProto(): Timestamp {
    return this.atTime(0, 0, 0)
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .let {
            Timestamp.newBuilder()
                .setSeconds(it.epochSecond)
                .setNanos(it.nano)
                .build()
        }
}
