package vn.tiger.notification.repositories.mongoose;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.tiger.notification.entities.mongoose.UserDeviceSetting;

public interface UserDeviceSettingRepository extends MongoRepository<UserDeviceSetting, String> {
}
