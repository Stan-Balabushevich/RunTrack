package id.slava.nt.run.network

import id.slava.nt.core.domain.location.Location
import id.slava.nt.core.domain.run.Run
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

fun RunDto.toRun(): Run {
    return Run(
        id = id,
        duration = durationMillis.milliseconds,
        dateTimeUtc = Instant.parse(dateTimeUtc)
            .atZone(ZoneId.of("UTC")),
        distanceMeters = distanceMeters,
        location = Location(lat, long),
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        mapPictureUrl = mapPictureUrl,
        avgHeartRate = avgHeartRate,
        maxHeartRate = maxHeartRate
    )
}

fun Run.toCreateRunRequest(): CreateRunRequest {
    return CreateRunRequest(
        id = id!!,
        durationMillis = duration.inWholeMilliseconds,
        distanceMeters = distanceMeters,
        lat = location.lat,
        long = location.long,
        avgSpeedKmh = avgSpeedKmh,
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        epochMillis = dateTimeUtc.toEpochSecond() * 1000L,
        avgHeartRate = avgHeartRate,
        maxHeartRate = maxHeartRate
    )
}


fun Run.toFirestoreMap(): Map<String, Any?> {
    return mapOf(
        "id" to id,
        "durationMillis" to duration.inWholeMilliseconds,
        "epochMillis" to dateTimeUtc.toInstant().toEpochMilli(),
        "distanceMeters" to distanceMeters,
        "lat" to location.lat,
        "long" to location.long,
        "maxSpeedKmh" to maxSpeedKmh,
        "totalElevationMeters" to totalElevationMeters,
        "mapPictureUrl" to mapPictureUrl,
        "avgHeartRate" to avgHeartRate,
        "maxHeartRate" to maxHeartRate
    )
}

fun Map<String, Any>.toRun(): Run {
    return Run(
        id = this["id"] as String,
        duration = (this["durationMillis"] as Long).milliseconds,
        dateTimeUtc = Instant.ofEpochMilli((this["epochMillis"] as Long))
            .atZone(ZoneId.of("UTC")),
        distanceMeters = (this["distanceMeters"] as Long).toInt(),
        location = Location(
            lat = this["lat"] as Double,
            long = this["long"] as Double
        ),
        maxSpeedKmh = this["maxSpeedKmh"] as Double,
        totalElevationMeters = (this["totalElevationMeters"] as Long).toInt(),
        mapPictureUrl = this["mapPictureUrl"] as String?,
        avgHeartRate = (this["avgHeartRate"] as Long?)?.toInt(),
        maxHeartRate = (this["maxHeartRate"] as Long?)?.toInt()
    )
}

