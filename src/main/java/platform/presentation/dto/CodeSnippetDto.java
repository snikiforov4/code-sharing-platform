package platform.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeSnippetDto {
    final String code;
    final String date;
    final long time;
    final int views;
}
