package com.gathin.tvhome;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/** Regression tests for the weather-only location catalogue. */
public class LocationAndLanguageTest {

    @Test
    public void testWeatherLocationCatalogueIncludesSupportedRegions() {
        List<String> regions = AppLocationManager.getAvailableRegions();
        assertTrue(regions.contains("Malaysia"));
        assertTrue(regions.contains("Indonesia"));
        assertTrue(regions.contains("Singapore"));
    }

    @Test
    public void testIndonesiaWeatherStationsAreAvailable() {
        List<AppLocationManager.Station> stations = AppLocationManager.getStationsForRegion("Indonesia");
        assertFalse(stations.isEmpty());
        assertTrue(stations.stream().anyMatch(s -> "Jakarta".equals(s.name)));
        assertTrue(stations.stream().anyMatch(s -> "Makassar".equals(s.name)));
    }
}
