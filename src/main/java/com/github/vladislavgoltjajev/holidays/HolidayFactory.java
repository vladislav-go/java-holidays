package com.github.vladislavgoltjajev.holidays;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@UtilityClass
public final class HolidayFactory {

    /**
     * @return Empty holiday container.
     */
    public Holidays empty() {
        return new Holidays();
    }

    /**
     * @param countryCode ISO 3166-1 alpha-3 country code.
     * @return Holiday container for the specified country.
     * @throws HolidayException Thrown when no JSON is found for the specified country.
     */
    public Holidays forCountry(String countryCode) throws HolidayException {
        countryCode = countryCode.toUpperCase();
        InputStream in = HolidayFactory.class.getResourceAsStream("/holidays/" + countryCode + ".json");

        if (in == null) {
            throw new HolidayException("No holidays found for country " + countryCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return new Gson().fromJson(reader, Holidays.class);
    }

    /**
     * @param json Holiday list JSON.
     * @return Holiday container for the custom holiday list JSON.
     */
    public Holidays custom(String json) {
        return new Gson().fromJson(json, Holidays.class);
    }
}
