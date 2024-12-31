package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    Optional<Invitation> findByToken(String token);

    Optional<Invitation> findByEmail(String email);
}
