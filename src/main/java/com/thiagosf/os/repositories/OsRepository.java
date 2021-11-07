package com.thiagosf.os.repositories;

import com.thiagosf.os.domain.Os;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<Os, Integer> {

}
