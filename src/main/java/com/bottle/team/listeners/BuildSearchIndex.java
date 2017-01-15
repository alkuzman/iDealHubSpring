package com.bottle.team.listeners;

import com.bottle.team.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BuildSearchIndex {

  private final IndexingService indexingService;

  @Autowired
  public BuildSearchIndex(IndexingService indexingService) {
    this.indexingService = indexingService;
  }


  @PostConstruct
  public void init() {
    //indexingService.createIndex();
  }
}
