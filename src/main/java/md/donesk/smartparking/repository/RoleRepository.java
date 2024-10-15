package md.donesk.smartparking.repository;

import md.donesk.smartparking.model.ERole;
import md.donesk.smartparking.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
