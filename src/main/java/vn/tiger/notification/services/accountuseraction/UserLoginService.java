package vn.tiger.notification.services.accountuseraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.common.utils.ObjectMapperUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.tiger.notification.constants.enums.AccountUserActionType;
import vn.tiger.notification.dtos.message.AccountLoginMessage;
import vn.tiger.notification.dtos.message.AccountUserActionMessage;
import vn.tiger.notification.entities.mongoose.UserDeviceToken;
import vn.tiger.notification.repositories.mongoose.UserDeviceTokenRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginService implements AccountUserActionService {

    final ObjectMapper objectMapper;
    final UserDeviceTokenRepository userDeviceTokenRepository;

    @Override
    public boolean isActionType(AccountUserActionType actionType) {
        return AccountUserActionType.LOGIN.equals(actionType);
    }

    @Override
    public void businessActionType(AccountUserActionMessage actionMessage) {
        log.info("[UserLoginService] action message {}", ObjectMapperUtil.castToString(actionMessage));
        AccountLoginMessage data = objectMapper.convertValue(actionMessage.getData(), AccountLoginMessage.class);
        UserAgent userAgent = UserAgent.parseUserAgentString(data.getDeviceName());
        log.info("[userAgent] data {}", ObjectMapperUtil.castToString(userAgent));

        // save user device token
        userDeviceTokenRepository.save(UserDeviceToken.builder()
                        .userId(data.getUserId().toString())
                        .sessionId(data.getSessionId())
                        .email(data.getEmail())
                        .username(data.getUsername())
                        .isExpire(false)
                        .ipAddress(data.getIpAddress())
                        .deviceType(userAgent.getOperatingSystem().getDeviceType().getName())
                        .deviceName(userAgent.getBrowser().name())
                        .tokenDevice(data.getTokenDevice())
                        .createdUser("system")
                        .createdDate(LocalDateTime.now())
                .build());
    }
}
