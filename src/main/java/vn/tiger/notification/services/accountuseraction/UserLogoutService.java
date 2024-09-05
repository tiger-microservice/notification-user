package vn.tiger.notification.services.accountuseraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.common.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.tiger.notification.constants.enums.AccountUserActionType;
import vn.tiger.notification.dtos.message.AccountLoginMessage;
import vn.tiger.notification.dtos.message.AccountLogoutMessage;
import vn.tiger.notification.dtos.message.AccountUserActionMessage;
import vn.tiger.notification.entities.mongoose.UserDeviceToken;
import vn.tiger.notification.repositories.mongoose.UserDeviceTokenRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLogoutService implements AccountUserActionService {

    final ObjectMapper objectMapper;
    final UserDeviceTokenRepository userDeviceTokenRepository;

    @Override
    public boolean isActionType(AccountUserActionType actionType) {
        return AccountUserActionType.LOGOUT.equals(actionType);
    }

    @Override
    public void businessActionType(AccountUserActionMessage actionMessage) {
        log.info("[UserLogoutService] action message {}", ObjectMapperUtil.castToString(actionMessage));
        AccountLogoutMessage data = objectMapper.convertValue(actionMessage.getData(), AccountLogoutMessage.class);

        // expire device token
        UserDeviceToken userDeviceToken = userDeviceTokenRepository.findFirstBySessionId(data.getSessionId())
                .orElse(null);
        if (userDeviceToken != null) {
            userDeviceToken.setIsExpire(true);
            userDeviceToken.setUpdatedUser("system");
            userDeviceToken.setUpdatedDate(LocalDateTime.now());
            userDeviceTokenRepository.save(userDeviceToken);
        } else {
            log.warn("[UserLogoutService] action logout session {} is not exist", data.getSessionId());
        }
    }
}
