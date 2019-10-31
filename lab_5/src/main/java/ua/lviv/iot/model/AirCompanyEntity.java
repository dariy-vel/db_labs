package ua.lviv.iot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "air_company", schema = "mydb")
public class AirCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
