package fr.doranco.primus_neon.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.doranco.primus_neon.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
