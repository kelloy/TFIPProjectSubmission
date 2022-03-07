package nusiss.csf.server.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import nusiss.csf.server.model.feedback;

public interface MongoDbRepo extends MongoRepository<feedback,String> {

    
}
