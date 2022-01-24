package platform.bussiness.code.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import platform.bussiness.code.model.CodeSnippet;

@Getter
@AllArgsConstructor
public class CodeSnippetContainer implements GetCodeSnippetResult {
    private final CodeSnippet codeSnippet;
}
