package vn.tiger.notification.services;

import com.tiger.common.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.tiger.notification.dtos.message.AccountUserActionMessage;
import vn.tiger.notification.repositories.AccountUserActionFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserService {

    final AccountUserActionFactory accountUserActionFactory;

    public void accountUserAction(String message) {
        try {
            AccountUserActionMessage accountUserActionMessage
                    = ObjectMapperUtil.castToObject(message, AccountUserActionMessage.class);

            accountUserActionFactory.getService(accountUserActionMessage.getActionType())
                    .businessActionType(accountUserActionMessage);
        } catch (Exception e) {
            log.error("[accountUserAction] error {}", e.getMessage(), e);
        }
    }
}
