package platform.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "code_snippets")
public class CodeSnippetEntity {
    @Id
    String id;
    LocalDateTime creationDate;
    String codeSnippet;
    @Nullable
    LocalDateTime availableUntilDate;
    @Nullable
    Integer leftViews;
}
