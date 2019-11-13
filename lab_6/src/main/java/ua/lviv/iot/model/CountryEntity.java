package ua.lviv.iot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "country", schema = "mydb")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<AirCompanyEntity> airCompanyEntities;

    public CountryEntity() {
    }

    public CountryEntity(final Integer id, final String name) {
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

    public List<AirCompanyEntity> getAirCompanyEntities() {
        return airCompanyEntities;
    }

    public void setAirCompanyEntities(final List<AirCompanyEntity> airCompanyEntities) {
        this.airCompanyEntities = airCompanyEntities;
    }

    @Override
    public String toString() {
        return "CountryEntity{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
