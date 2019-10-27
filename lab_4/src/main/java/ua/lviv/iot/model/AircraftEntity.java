package ua.lviv.iot.model;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.PrimaryKey;
import ua.lviv.iot.model.Annotation.Table;

@Table(name = "aircraft")
public class AircraftEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "board_number", length = 10)
    private String boardNumber;

    @Column(name = "registration_number", length = 15)
    private String registrationNumber;

    @Column(name = "registration_country_id")
    private Integer registrationCountryId;

    @Column(name = "air_company_id")
    private Integer airCompanyId;

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    public AircraftEntity() {
    }

    public AircraftEntity(final Integer id, final String boardNumber, final String registrationNumber,
                          final Integer registrationCountryId, final Integer airCompanyId, final Integer modelId,
                          final Double longitude, final Double latitude) {
        this.id = id;
        this.boardNumber = boardNumber;
        this.registrationNumber = registrationNumber;
        this.registrationCountryId = registrationCountryId;
        this.airCompanyId = airCompanyId;
        this.modelId = modelId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(final String boardNumber) {
        this.boardNumber = boardNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getRegistrationCountryId() {
        return registrationCountryId;
    }

    public void setRegistrationCountryId(final Integer registrationCountryId) {
        this.registrationCountryId = registrationCountryId;
    }

    public Integer getAirCompanyId() {
        return airCompanyId;
    }

    public void setAirCompanyId(final Integer airCompanyId) {
        this.airCompanyId = airCompanyId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(final Integer modelId) {
        this.modelId = modelId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "AircraftEntity{"
                + "id=" + id
                + ", boardNumber='" + boardNumber + '\''
                + ", registrationNumber='" + registrationNumber + '\''
                + ", registrationCountryId=" + registrationCountryId
                + ", airCompanyId=" + airCompanyId
                + ", modelId=" + modelId
                + ", longitude=" + longitude
                + ", latitude=" + latitude
                + '}';
    }
}
