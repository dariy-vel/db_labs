package ua.lviv.iot.model;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.PrimaryKey;
import ua.lviv.iot.model.Annotation.Table;

import java.sql.Timestamp;

@Table(name = "flight")
public class FlightEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "aircraft_id")
    private Integer aircraft_id;

    @Column(name = "from_airport_id")
    private Integer fromAirportId;

    @Column(name = "to_airport_id")
    private Integer toAirportId;

    @Column(name = "scheduled_departure_time")
    private Timestamp scheduledDepartureTime;

    @Column(name = "scheduled_arrival_time")
    private Timestamp scheduledArrivalTime;

    @Column(name = "actual_departure_time")
    private Timestamp actualDepartureTime;

    @Column(name = "est_arrival_time")
    private Timestamp estArrivalTime;

    public FlightEntity() {
    }

    public FlightEntity(final Integer id, final Integer aircraft_id, final Integer fromAirportId,
                        final Integer toAirportId, final Timestamp scheduledDepartureTime,
                        final Timestamp scheduledArrivalTime, final Timestamp actualDepartureTime,
                        final Timestamp estArrivalTime) {
        this.id = id;
        this.aircraft_id = aircraft_id;
        this.fromAirportId = fromAirportId;
        this.toAirportId = toAirportId;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.estArrivalTime = estArrivalTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAircraft_id() {
        return aircraft_id;
    }

    public void setAircraft_id(final Integer aircraft_id) {
        this.aircraft_id = aircraft_id;
    }

    public Integer getFromAirportId() {
        return fromAirportId;
    }

    public void setFromAirportId(final Integer fromAirportId) {
        this.fromAirportId = fromAirportId;
    }

    public Integer getToAirportId() {
        return toAirportId;
    }

    public void setToAirportId(final Integer toAirportId) {
        this.toAirportId = toAirportId;
    }

    public Timestamp getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(final Timestamp scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public Timestamp getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(final Timestamp scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public Timestamp getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(final Timestamp actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public Timestamp getEstArrivalTime() {
        return estArrivalTime;
    }

    public void setEstArrivalTime(final Timestamp estArrivalTime) {
        this.estArrivalTime = estArrivalTime;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id=" + id +
                ", aircraft_id=" + aircraft_id +
                ", fromAirportId=" + fromAirportId +
                ", toAirportId=" + toAirportId +
                ", scheduledDepartureTime=" + scheduledDepartureTime +
                ", scheduledArrivalTime=" + scheduledArrivalTime +
                ", actualDepartureTime=" + actualDepartureTime +
                ", estArrivalTime=" + estArrivalTime +
                '}';
    }
}
