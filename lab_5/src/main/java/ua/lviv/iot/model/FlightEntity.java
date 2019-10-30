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
    private Integer aircraftId;

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

    public FlightEntity(final Integer id, final Integer aircraftId, final Integer fromAirportId,
                        final Integer toAirportId, final Timestamp scheduledDepartureTime,
                        final Timestamp scheduledArrivalTime, final Timestamp actualDepartureTime,
                        final Timestamp estArrivalTime) {
        this.id = id;
        this.aircraftId = aircraftId;
        this.fromAirportId = fromAirportId;
        this.toAirportId = toAirportId;
        this.scheduledDepartureTime = new Timestamp(scheduledDepartureTime.getTime());
        this.scheduledArrivalTime = new Timestamp(scheduledArrivalTime.getTime());
        this.actualDepartureTime = new Timestamp(actualDepartureTime.getTime());
        this.estArrivalTime = new Timestamp(estArrivalTime.getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(final Integer aircraftId) {
        this.aircraftId = aircraftId;
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
        return new Timestamp(scheduledDepartureTime.getTime());
    }

    public void setScheduledDepartureTime(final Timestamp scheduledDepartureTime) {
        this.scheduledDepartureTime = new Timestamp(scheduledDepartureTime.getTime());
    }

    public Timestamp getScheduledArrivalTime() {
        return new Timestamp(scheduledArrivalTime.getTime());
    }

    public void setScheduledArrivalTime(final Timestamp scheduledArrivalTime) {
        this.scheduledArrivalTime = new Timestamp(scheduledArrivalTime.getTime());
    }

    public Timestamp getActualDepartureTime() {
        return new Timestamp(actualDepartureTime.getTime());
    }

    public void setActualDepartureTime(final Timestamp actualDepartureTime) {
        this.actualDepartureTime = new Timestamp(actualDepartureTime.getTime());
    }

    public Timestamp getEstArrivalTime() {
        return new Timestamp(estArrivalTime.getTime());
    }

    public void setEstArrivalTime(final Timestamp estArrivalTime) {
        this.estArrivalTime = new Timestamp(estArrivalTime.getTime());
    }

    @Override
    public String toString() {
        return "FlightEntity{"
                + "id=" + id
                + ", aircraft_id=" + aircraftId
                + ", fromAirportId=" + fromAirportId
                + ", toAirportId=" + toAirportId
                + ", scheduledDepartureTime=" + scheduledDepartureTime
                + ", scheduledArrivalTime=" + scheduledArrivalTime
                + ", actualDepartureTime=" + actualDepartureTime
                + ", estArrivalTime=" + estArrivalTime
                + '}';
    }
}
