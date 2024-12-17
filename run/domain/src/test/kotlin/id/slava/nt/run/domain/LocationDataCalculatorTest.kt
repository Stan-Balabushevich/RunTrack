package id.slava.nt.run.domain

import id.slava.nt.core.domain.location.Location
import id.slava.nt.core.domain.location.LocationTimestamp
import id.slava.nt.core.domain.location.LocationWithAltitude
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class LocationDataCalculatorTest {

    private lateinit var locationDataCalculator: LocationDataCalculator

    @BeforeEach
    fun setUp() {
        locationDataCalculator = LocationDataCalculator
    }

    @Test
    fun `getMaxSpeed returns correct max speed`() {
        val location1 = Location(52.5200, 13.4050)
        val location2 = Location(52.5205, 13.4055)
        val location3 = Location(52.5210, 13.4060)

        val timestamp1 = 0.seconds
        val timestamp2 = 10.seconds
        val timestamp3 = 20.seconds

        val locationTimestamp1 = LocationTimestamp(LocationWithAltitude(location1, 10.0), timestamp1)
        val locationTimestamp2 = LocationTimestamp(LocationWithAltitude(location2, 12.0), timestamp2)
        val locationTimestamp3 = LocationTimestamp(LocationWithAltitude(location3, 15.0), timestamp3)

        val locations = listOf(
            listOf(locationTimestamp1, locationTimestamp2),
            listOf(locationTimestamp2, locationTimestamp3)
        )

        // Expected speed in m/s
        val expectedMaxSpeed = 6.22 // Example value (calculate manually)

        // Call the function under test
        val result = locationDataCalculator.getMaxSpeedKmh(locations)

        // Assert with a tolerance for floating-point differences
        assertEquals(expectedMaxSpeed, result, 0.1)
    }


}