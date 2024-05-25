package br.com.pondionstracker.bh.realtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pondionstracker.bh.realtime.models.BusLine;

@Repository
public interface BusLineRepository extends JpaRepository<BusLine, Integer> {

}
