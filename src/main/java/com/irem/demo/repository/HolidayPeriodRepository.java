package com.irem.demo.repository;

import com.irem.demo.model.HolidayPeriod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface HolidayPeriodRepository extends JpaRepository<HolidayPeriod, Long> {

    @Query("""
        SELECT hp FROM HolidayPeriod hp
        JOIN FETCH hp.definition def
        JOIN FETCH def.type
        WHERE EXTRACT(YEAR FROM hp.startDate) = :year
        AND def.id IN :definitionIds
    """)
    List<HolidayPeriod> findByYearAndDefinitionIds(@Param("year") int year,
                                                    @Param("definitionIds") Set<Long> definitionIds);
}
