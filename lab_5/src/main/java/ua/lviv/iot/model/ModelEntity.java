package ua.lviv.iot.model;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.PrimaryKey;
import ua.lviv.iot.model.Annotation.Table;

@Table(name = "model")
public class ModelEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "manufacturer", length = 45)
    private String manufacturer;

    public ModelEntity() {
    }

    public ModelEntity(final Integer id, final String name, final String manufacturer) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ModelEntity{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", manufacturer='" + manufacturer + '\''
                + '}';
    }
}
