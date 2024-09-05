package vn.tiger.notification.dtos.message;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.tiger.notification.constants.enums.AccountUserActionType;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUserActionMessage<T> {
    UUID id;
    AccountUserActionType actionType; // login, logout, deactivate, active, lock
    T data;
}
