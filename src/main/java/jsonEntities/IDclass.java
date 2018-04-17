package jsonEntities;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.mongodb.core.mapping.Field;

public class IDclass {
    private String oid;

    public IDclass() {
    }

    public String get$oid() {
        return oid;
    }

    public void set$oid(String $oid) {
        this.oid = $oid;
    }
}
