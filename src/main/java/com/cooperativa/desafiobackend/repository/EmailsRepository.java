package com.cooperativa.desafiobackend.repository;

import com.cooperativa.desafiobackend.model.Emails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailsRepository extends JpaRepository<Emails, Long> {

}
