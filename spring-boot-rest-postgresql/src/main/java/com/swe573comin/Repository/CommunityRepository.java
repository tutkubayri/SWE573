package com.swe573comin.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.swe573comin.Model.Community;

public interface CommunityRepository extends CrudRepository<Community, Long> {
	List<Community> findByName(String name);
	List<Community> findBySemanticTag(String semanticTag);
}