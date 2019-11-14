package com.swe.comin.repositories;

import com.swe.comin.models.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Long> {
    public List<PostType> findByCommunityId(Long communityId);
}