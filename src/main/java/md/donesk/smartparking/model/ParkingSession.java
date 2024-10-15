package md.donesk.smartparking.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_parking_session")
@Data
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "licence_place")
    private String licensePlate;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "parking_zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingZone parkingZone;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Transient
    private Long currentDuration;



}
