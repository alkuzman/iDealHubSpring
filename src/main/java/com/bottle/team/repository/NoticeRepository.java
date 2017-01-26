package com.bottle.team.repository;

import com.bottle.team.model.sharing.Notice;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 1/26/2017.
 */
public interface NoticeRepository extends GraphRepository<Notice> {
}
