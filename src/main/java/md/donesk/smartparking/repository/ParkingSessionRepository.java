package md.donesk.smartparking.repository;

import md.donesk.smartparking.model.ParkingSession;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    @EntityGraph(attributePaths = {"user.roles"})
    @Query("SELECT ps FROM ParkingSession ps JOIN FETCH ps.user u JOIN FETCH u.roles WHERE ps.user.id = :userId")
    List<ParkingSession> getParkingSessionByUserId(Long userId);

    @Query("SELECT ps FROM ParkingSession ps JOIN FETCH ps.user")
    List<ParkingSession> findAllWithUsers();
}
