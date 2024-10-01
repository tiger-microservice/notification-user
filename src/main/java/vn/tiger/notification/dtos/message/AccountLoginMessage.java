package vn.tiger.notification.dtos.message;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountLoginMessage {
    String sessionId;
    UUID userId;
    String username;
    String email;
    String phoneNumber;
    String tokenDevice;
    String deviceName;
    String deviceType;
    String ipAddress;
}
