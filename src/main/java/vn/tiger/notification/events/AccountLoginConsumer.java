package vn.tiger.notification.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vn.tiger.notification.constants.AppConstants;
import vn.tiger.notification.services.AccountUserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountLoginConsumer {

    final AccountUserService accountUserService;

    @KafkaListener(
            topics = {"${spring.kafka.consumer.account-user-action:account-user-action}"},
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeAccountUserAction(@Payload String message,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("[consumeAccountUserAction] message {}", message);
        log.info("[consumeAccountUserAction] topic {}", topic);
        log.info("[consumeAccountUserAction] key {}", key);
        setRequestId(key);
        this.accountUserService.accountUserAction(message);
    }

    private void setRequestId(String key) {
        MDC.put(AppConstants.MDC_CORRELATION_ID, key);
    }
}
