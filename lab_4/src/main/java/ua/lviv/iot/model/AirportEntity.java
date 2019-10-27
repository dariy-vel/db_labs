package ua.lviv.iot.model;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.PrimaryKey;
import ua.lviv.iot.model.Annotation.Table;

@Table(name = "airport")
public class AirportEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 90)
    private String name;

    public AirportEntity() {
    }

    public AirportEntity(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirportEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
