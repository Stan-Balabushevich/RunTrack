package id.slava.nt.run.location

import android.location.Location
import id.slava.nt.core.domain.location.LocationWithAltitude


fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = id.slava.nt.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}