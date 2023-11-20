package fr.univtln.bruno.samples.jakartaee.jpajaxrs;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@NamedQuery(name="Message.findAll",query = "select m from Message m")
@Table(name = "MESSAGE")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Getter
@ToString
@JsonPropertyOrder({ "id", "content" })
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Message {
    @Id
    @Column(name = "ID")
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    @Column(name = "CONTENT")
    @NotBlank
    private String content;
}
