package md.donesk.smartparking.repository;

import md.donesk.smartparking.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
    List<ParkingSlot> findByLocation(String location);
    List<ParkingSlot> findByAvailable(boolean available);
    List<ParkingSlot> findByLocationAndAvailable(String location, boolean available);
}
