package vn.tiger.notification.services.accountuseraction;

import vn.tiger.notification.constants.enums.AccountUserActionType;
import vn.tiger.notification.dtos.message.AccountUserActionMessage;

public interface AccountUserActionService {

    boolean isActionType(AccountUserActionType actionType);

    void businessActionType(AccountUserActionMessage actionMessage);
}
