package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class MonitoredEntityListener {
    @PrePersist
    protected void prePersist(Object object) {
        if (object instanceof MonitoredEntity monitoredEntity) {
            if (monitoredEntity.getCreationTime() == null) monitoredEntity.setCreationTime(LocalDateTime.now());
            monitoredEntity.setLastModifiedTime(LocalDateTime.now());
        }
    }

    @PreUpdate
    @PreRemove
    protected void preUpdate(Object object) {
        if (object instanceof MonitoredEntity monitoredEntity)
            monitoredEntity.setLastModifiedTime(LocalDateTime.now());
    }

}
