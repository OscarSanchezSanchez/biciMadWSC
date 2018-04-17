package jsonEntities;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class UnplugTime {
    private Date date;

    public UnplugTime() {
    }

    public Date get$date() {
        return date;
    }

    public void set$date(Date $date) {
        this.date = $date;
    }
}
