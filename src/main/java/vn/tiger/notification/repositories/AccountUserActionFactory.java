package vn.tiger.notification.repositories;

import com.tiger.cores.exceptions.BusinessLogicException;
import com.tiger.cores.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.tiger.notification.constants.enums.AccountUserActionType;
import vn.tiger.notification.services.accountuseraction.AccountUserActionService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountUserActionFactory {
    final List<AccountUserActionService> services;

    public AccountUserActionService getService(AccountUserActionType actionType) {
        return services.stream().filter(item -> item.isActionType(actionType))
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.BEAN_NOT_DEFINED));
    }
}
