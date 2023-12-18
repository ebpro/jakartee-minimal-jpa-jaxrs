package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import java.time.LocalDateTime;

public interface MonitoredEntity {
    LocalDateTime getCreationTime();
    LocalDateTime getLastModifiedTime();

     void setCreationTime(LocalDateTime creationTime);
     void setLastModifiedTime(LocalDateTime lastModifiedTime);
}
