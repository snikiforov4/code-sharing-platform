package platform.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.bussiness.code.model.CodeSnippet;
import platform.bussiness.code.service.CodeSnippetService;
import platform.presentation.dto.CodeSnippetDto;
import platform.presentation.mapper.CodeSnippetDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CodeController {

    private final CodeSnippetService codeSnippetService;
    private final CodeSnippetDtoMapper codeSnippetMapper;

    @GetMapping("/code/new")
    String showAddNewCodeSnippet() {
        return "new_code_snippet";
    }

    @GetMapping("/code/{id}")
    String showCodeSnippet(@PathVariable String id, Model model) {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippetById(id).extract();
        model.addAttribute("codeSnippet", codeSnippetMapper.toViewDto(codeSnippet));
        return "show_code_snippet";
    }

    @GetMapping("/code/latest")
    String showCodeSnippet(Model model) {
        List<CodeSnippetDto> result = codeSnippetService.get10LatestUploadedCodeSnippets()
                .stream()
                .map(codeSnippetMapper::toViewDto)
                .collect(Collectors.toList());
        model.addAttribute("codeSnippets", result);
        return "show_latest_code_snippets";
    }

}
