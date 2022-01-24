package platform.bussiness.code.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodeSnippetAccessRestrictionException extends RuntimeException {
}
