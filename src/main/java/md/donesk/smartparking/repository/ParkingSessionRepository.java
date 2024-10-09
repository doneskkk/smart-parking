package md.donesk.smartparking.repository;

import md.donesk.smartparking.model.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
}
