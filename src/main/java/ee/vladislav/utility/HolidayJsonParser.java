package ee.vladislav.utility;

import ee.vladislav.enums.Location;
import ee.vladislav.objects.HolidayContainer;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class HolidayJsonParser {

    private Gson gson = new Gson();

    public Map<Location, HolidayContainer> getAllHolidays() {
        Map<Location, HolidayContainer> holidays = new HashMap<>();

        for (Location location : Location.values()) {
            if (Location.CUSTOM.equals(location)) {
                continue;
            }

            HolidayContainer container;

            try (InputStream in = getClass().getResourceAsStream("/holidays/" + location.name().toLowerCase()
                    + ".json");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                container = gson.fromJson(IOUtils.toString(reader), HolidayContainer.class);
            } catch (IOException e) {
                continue;
            }

            if (container != null) {
                holidays.put(location, container);
            }
        }

        return holidays;
    }
}
