package ee.vladislav.objects;

import lombok.Data;

@Data
public final class MovableHoliday {

    private String code;
    private String name;
    private boolean dayOff;
}
