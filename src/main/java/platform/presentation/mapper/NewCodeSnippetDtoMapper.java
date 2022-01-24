package platform.presentation.mapper;

import org.springframework.stereotype.Component;
import platform.bussiness.code.model.NewCodeSnippet;
import platform.presentation.dto.NewCodeSnippetRequestDto;

@Component
public class NewCodeSnippetDtoMapper {

    public NewCodeSnippet fromDto(NewCodeSnippetRequestDto dto) {
        return new NewCodeSnippet(
                dto.getCode(),
                dto.getTime(),
                dto.getViews()
        );
    }

}
