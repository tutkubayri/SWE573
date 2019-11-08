package com.swe573.comin.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.swe573.comin.Models.Community;

public interface CommunityRepository extends CrudRepository<Community, Long> {
	List<Community> findByName(String name);
	List<Community> findBySemanticTag(String semanticTag);
}