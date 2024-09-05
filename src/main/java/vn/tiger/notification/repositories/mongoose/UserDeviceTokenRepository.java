package vn.tiger.notification.repositories.mongoose;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.tiger.notification.entities.mongoose.UserDeviceToken;

import java.util.List;
import java.util.Optional;

public interface UserDeviceTokenRepository extends MongoRepository<UserDeviceToken, String> {
    List<UserDeviceToken> findAllByUserId(String userId);

    Optional<UserDeviceToken> findFirstBySessionId(String sessionId);
}
