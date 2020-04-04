package com.github.vladislavgoltjajev.holidays;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Holiday {

    protected String name;
    protected transient LocalDate date;

    Holiday() {
    }
}
