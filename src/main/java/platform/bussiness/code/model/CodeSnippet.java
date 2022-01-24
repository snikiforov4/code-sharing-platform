package platform.bussiness.code.model;

import lombok.*;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Optional;
import java.util.OptionalInt;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSnippet {
    String id;
    Instant creationDate;
    @NonNull
    String code;
    @Nullable
    Instant availableUntilDate;
    @Nullable
    Integer viewsLeft;

    public Optional<Instant> getAvailableUntilDate() {
        return Optional.ofNullable(availableUntilDate);
    }

    public OptionalInt getViewsLeft() {
        return viewsLeft == null ? OptionalInt.empty() : OptionalInt.of(viewsLeft);
    }
}
