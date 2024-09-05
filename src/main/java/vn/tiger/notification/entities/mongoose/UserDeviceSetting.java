package vn.tiger.notification.entities.mongoose;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@Data
@Builder
@Document(collection = "user_device_token")
@CompoundIndex(name = "user_device_token_user_id_status", def="{'user_id': 1, 'is_expire': 1}")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDeviceSetting {
    @Id
    String userId;
    @Field(value = "notify_channel")
    String notifyChannel; // email, sms, notify
    @Field(value = "notify_setting")
    String notifySetting; // ON, OFF
    @Field(value = "email")
    String email;
    @Field(value = "username")
    String username;
    @Field(value = "phone_number")
    String phoneNumber;
    @Field(value = "created_date")
    LocalDateTime createdDate;
    @Field(value = "created_user")
    String createdUser;
    @Field(value = "updated_date")
    LocalDateTime updatedDate;
    @Field(value = "updated_user")
    String updatedUser;
}
