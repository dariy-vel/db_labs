package ua.lviv.iot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public AirCompanyEntity() {
    }

    public AirCompanyEntity(final Integer id, final String name, final CountryEntity countryEntity) {
        this.id = id;
        this.name = name;
        this.country = countryEntity;
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

    public CountryEntity getCountryEntity() {
        return country;
    }

    public void setCountryEntity(final CountryEntity countryEntity) {
        this.country = countryEntity;
    }

    @Override
    public String toString() {
        return "AirCompanyEntity{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country_id=" + country.getId()
                + '}';
    }
}
