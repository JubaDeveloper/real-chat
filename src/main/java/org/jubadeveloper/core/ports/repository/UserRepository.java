package org.jubadeveloper.core.ports.repository;

import org.jubadeveloper.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmailAndPassword (String email, String password);
}
