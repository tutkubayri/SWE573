package com.swe.comin.repositories;

import com.swe.comin.models.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnumRepository extends JpaRepository<EnumType, Long> {
    public List<EnumType> findByFormAreaId(Long formArea);
}
