package id.slava.nt.run.presentation.run_overview.mapper

import id.slava.nt.core.domain.run.Run
import id.slava.nt.core.presentation.ui.formatted
import id.slava.nt.core.presentation.ui.toFormattedHeartRate
import id.slava.nt.core.presentation.ui.toFormattedKm
import id.slava.nt.core.presentation.ui.toFormattedKmh
import id.slava.nt.core.presentation.ui.toFormattedMeters
import id.slava.nt.core.presentation.ui.toFormattedPace
import id.slava.nt.run.presentation.run_overview.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formattedDateTime = DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)

    val distanceKm = distanceMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formattedDateTime,
        distance = distanceKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl,
        avgHeartRate = avgHeartRate.toFormattedHeartRate(),
        maxHeartRate = maxHeartRate.toFormattedHeartRate()
    )
}