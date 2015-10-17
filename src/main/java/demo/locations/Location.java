package demo.locations;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
