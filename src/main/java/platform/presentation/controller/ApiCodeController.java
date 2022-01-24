package platform.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import platform.bussiness.code.model.CodeSnippet;
import platform.bussiness.code.service.CodeSnippetService;
import platform.presentation.dto.CodeSnippetDto;
import platform.presentation.dto.NewCodeSnippetRequestDto;
import platform.presentation.dto.NewCodeSnippetResponseDto;
import platform.presentation.mapper.CodeSnippetDtoMapper;
import platform.presentation.mapper.NewCodeSnippetDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ApiCodeController {

    private final CodeSnippetService codeSnippetService;
    private final CodeSnippetDtoMapper codeSnippetMapper;
    private final NewCodeSnippetDtoMapper newCodeSnippetMapper;

    @PostMapping("/api/code/new")
    @ResponseBody
    NewCodeSnippetResponseDto addNewCodeSnippet(@RequestBody NewCodeSnippetRequestDto request) {
        CodeSnippet newCodeSnippet = codeSnippetService.createNewCodeSnippet(newCodeSnippetMapper.fromDto(request));
        return new NewCodeSnippetResponseDto(newCodeSnippet.getId());
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    CodeSnippetDto getCodeSnippet(@PathVariable String id) {
        var result = codeSnippetService.getCodeSnippetById(id);
        return codeSnippetMapper.toDto(result.extract());
    }


    @GetMapping("/api/code/latest")
    @ResponseBody
    List<CodeSnippetDto> getLatestCodeSnippet() {
        return codeSnippetService.get10LatestUploadedCodeSnippets()
                .stream()
                .map(codeSnippetMapper::toDto)
                .collect(Collectors.toList());
    }

}
