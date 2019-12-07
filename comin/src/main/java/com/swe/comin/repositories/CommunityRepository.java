package com.swe.comin.repositories;

import com.swe.comin.models.Community;
import com.swe.comin.models.FormArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long>{
}
