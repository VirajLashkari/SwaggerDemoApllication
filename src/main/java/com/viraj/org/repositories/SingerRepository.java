package com.viraj.org.repositories;

import com.viraj.org.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Long> {
}
