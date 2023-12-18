package fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NamedQuery(name="Message.findAll",query = "select m from Message m order by m.id")
@Table(name = "MESSAGE")
@EntityListeners(MonitoredEntityListener.class)

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@ToString(onlyExplicitlyIncluded = true)
@Getter

@XmlRootElement
@JsonPropertyOrder({ "id", "content" })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Message implements MonitoredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    @ToString.Include
    private UUID id;

    @NotNull
    @NonNull
    @NotBlank
    @Column(name = "CONTENT")
    @ToString.Include
    private String content;

    @Setter
    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;

    @Setter
    @Column(name = "UPDATE_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModifiedTime;
}
