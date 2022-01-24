package platform.bussiness.code.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeSnippetAccessError implements GetCodeSnippetResult {

    private final Error error;

    public enum Error {
        RESTRICTION_ACCESS
    }
}
