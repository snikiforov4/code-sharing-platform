package platform.presentation.dto;

import lombok.Data;

@Data
public class NewCodeSnippetRequestDto {
    String code;
    long time;
    int views;
}
