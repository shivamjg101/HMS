package org.hms.userms.repository;


import org.hms.userms.dto.MonthlyRoleCountDTO;
import org.hms.userms.dto.Roles;
import org.hms.userms.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    @Query("Select new org.hms.userms.dto.MonthlyRoleCountDTO( CAST(FUNCTION('MONTHNAME', a.createdAt) as String) , COUNT(a)) FROM Users a WHERE a.role = ?1 AND YEAR(a.createdAt) = YEAR(CURRENT_DATE) GROUP BY FUNCTION('MONTH', a.createdAt), CAST(FUNCTION('MONTHNAME', a.createdAt) as String) ORDER BY FUNCTION('MONTH', a.createdAt)")
    List<MonthlyRoleCountDTO> countRegistrationsByRoleGroupedByMonth(Roles role);
}
