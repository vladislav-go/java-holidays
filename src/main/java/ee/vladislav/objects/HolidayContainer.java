package ee.vladislav.objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public final class HolidayContainer {

    private List<Holiday> fixedHolidays = new ArrayList<>();
    private List<MovableHoliday> movableHolidays = new ArrayList<>();
}
