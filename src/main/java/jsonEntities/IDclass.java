package jsonEntities;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.mongodb.core.mapping.Field;

public class IDclass {
    private String oid;

    public IDclass() {
    }

    public String getoid() {
        return oid;
    }

    public void setoid(String $oid) {
        this.oid = $oid;
    }
}
