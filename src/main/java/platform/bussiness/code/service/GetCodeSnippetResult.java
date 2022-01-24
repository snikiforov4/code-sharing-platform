package platform.bussiness.code.service;

import platform.bussiness.code.model.CodeSnippet;

public interface GetCodeSnippetResult {

    static CodeSnippetContainer ofCodeSnippet(CodeSnippet codeSnippet) {
        return new CodeSnippetContainer(codeSnippet);
    }

    static CodeSnippetAccessError error(CodeSnippetAccessError.Error error) {
        return new CodeSnippetAccessError(error);
    }

    default CodeSnippet extract() {
        if (this instanceof CodeSnippetContainer container) {
            return container.getCodeSnippet();
        } else if (this instanceof CodeSnippetAccessError) {
            throw new CodeSnippetAccessRestrictionException();
        } else {
            throw new IllegalStateException("Unexpected value: " + getClass());
        }
    }
}
