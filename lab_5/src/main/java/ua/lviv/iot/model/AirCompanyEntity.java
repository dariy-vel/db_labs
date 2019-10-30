package ua.lviv.iot.model;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.PrimaryKey;
import ua.lviv.iot.model.Annotation.Table;

@Table(name = "air_company")
public class AirCompanyEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "country_id")
    private Integer countryId;

    public AirCompanyEntity() {
    }

    public AirCompanyEntity(final Integer id, final String name, final Integer countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(final Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "AirCompanyEntity{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country_id=" + countryId
                + '}';
    }
}
