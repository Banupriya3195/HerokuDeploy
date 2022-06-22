package com.vmi.module.service;

import com.vmi.module.model.DatabaseSequence;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService
{
  private MongoOperations mongoOperations;
  
  @Autowired
  public SequenceGeneratorService(MongoOperations mongoOperations)
  {
    this.mongoOperations = mongoOperations;
  }
  
  public long generateSequence(String seqName)
  {
    DatabaseSequence counter = (DatabaseSequence)this.mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)), new Update()
      .inc("seq", Integer.valueOf(1)), FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSequence.class);
    
    return !Objects.isNull(counter) ? counter.getSeq() : 1L;
  }
}
