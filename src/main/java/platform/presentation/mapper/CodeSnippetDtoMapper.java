package platform.presentation.mapper;

import org.springframework.stereotype.Component;
import platform.bussiness.code.model.CodeSnippet;
import platform.presentation.dto.CodeSnippetDto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class CodeSnippetDtoMapper {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss")
            .withZone(ZoneId.from(ZoneOffset.UTC));

    public CodeSnippetDto toDto(CodeSnippet codeSnippet) {
        return new CodeSnippetDto(
                codeSnippet.getCode(),
                DATE_FORMATTER.format(codeSnippet.getCreationDate()),
                getTtl(codeSnippet, 0L),
                getViews(codeSnippet, 0)
        );
    }

    public CodeSnippetDto toViewDto(CodeSnippet codeSnippet) {
        return new CodeSnippetDto(
                codeSnippet.getCode(),
                DATE_FORMATTER.format(codeSnippet.getCreationDate()),
                getTtl(codeSnippet, -1L),
                getViews(codeSnippet, -1)
        );
    }

    private int getViews(CodeSnippet codeSnippet, int defaultValue) {
        return codeSnippet.getViewsLeft().orElse(defaultValue);
    }

    private long getTtl(CodeSnippet codeSnippet, long defaultValue) {
        Instant now = Instant.now();
        return codeSnippet.getAvailableUntilDate()
                .map(date -> Math.max(date.getEpochSecond() - now.getEpochSecond(), 0))
                .orElse(defaultValue);
    }

}
