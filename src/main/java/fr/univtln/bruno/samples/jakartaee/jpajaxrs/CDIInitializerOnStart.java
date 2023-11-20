package fr.univtln.bruno.samples.jakartaee.jpajaxrs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class CDIInitializerOnStart {
    private final MessageDAO messageDAO;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object object) {
        log.info("Init CDI singleton");
        messageDAO.create(Message.of(UUID.randomUUID(),"Test message from initialisation singleton."));
    }
}
