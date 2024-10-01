package vn.tiger.notification.dtos.message;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountLogoutMessage {
    UUID userId;
    String sessionId;
}
