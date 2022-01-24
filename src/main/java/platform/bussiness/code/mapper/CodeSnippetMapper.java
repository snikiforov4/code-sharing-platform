package platform.bussiness.code.mapper;

import org.springframework.stereotype.Component;
import platform.bussiness.code.model.CodeSnippet;
import platform.persistence.entity.CodeSnippetEntity;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class CodeSnippetMapper {

    public CodeSnippet toModel(CodeSnippetEntity entity) {
        return new CodeSnippet(
                entity.getId(),
                entity.getCreationDate().toInstant(ZoneOffset.UTC),
                entity.getCodeSnippet(),
                convertAvailableUntilDate(entity.getAvailableUntilDate()),
                entity.getLeftViews() != null ? entity.getLeftViews() : null
        );
    }

    @Nullable
    private Instant convertAvailableUntilDate(@Nullable LocalDateTime availableUntilDate) {
        Instant result = null;
        if (availableUntilDate != null) {
            result = availableUntilDate.toInstant(ZoneOffset.UTC);
        }
        return result;
    }

}
