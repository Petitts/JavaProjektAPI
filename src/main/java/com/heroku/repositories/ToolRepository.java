package com.heroku.repositories;


import com.heroku.models.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToolRepository extends MongoRepository<Tool, String> {
    Tool findToolByTypeAndTitle(String type, String title);
    List<Tool> findDistinctByA(String a);
    List<Tool> findDistinctByAAndType(String a, String type);
}
