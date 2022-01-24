package platform.bussiness.code.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import platform.bussiness.code.mapper.CodeSnippetMapper;
import platform.bussiness.code.model.CodeSnippet;
import platform.bussiness.code.model.NewCodeSnippet;
import platform.persistence.entity.CodeSnippetEntity;
import platform.persistence.repository.CodeSnippetRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CodeSnippetService {

    private final CodeSnippetRepository codeSnippetRepository;
    private final CodeSnippetMapper codeSnippetMapper;

    public CodeSnippet createNewCodeSnippet(NewCodeSnippet newCodeSnippet) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        CodeSnippetEntity entity = new CodeSnippetEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCodeSnippet(newCodeSnippet.getCode());
        entity.setCreationDate(now);
        if (newCodeSnippet.getTtlInSeconds() > 0) {
            entity.setAvailableUntilDate(now.plusSeconds(newCodeSnippet.getTtlInSeconds()));
        }
        if (newCodeSnippet.getViewsLeft() > 0) {
            entity.setLeftViews(newCodeSnippet.getViewsLeft());
        }
        entity = codeSnippetRepository.save(entity);
        return codeSnippetMapper.toModel(entity);
    }

    public GetCodeSnippetResult getCodeSnippetById(String id) {
        CodeSnippetEntity entity = codeSnippetRepository.findById(id)
                .orElseThrow(CodeSnippetNotFoundException::new);
        RestrictionsCheckResult checkResult = checkRestrictions(entity);
        return switch (checkResult) {
            case ACCESS_ALLOWED -> GetCodeSnippetResult.ofCodeSnippet(codeSnippetMapper.toModel(entity));
            case ENTITY_UPDATE_REQUIRED -> {
                entity = codeSnippetRepository.save(entity);
                yield GetCodeSnippetResult.ofCodeSnippet(codeSnippetMapper.toModel(entity));
            }
            case ACCESS_DENIED -> {
                codeSnippetRepository.delete(entity);
                yield GetCodeSnippetResult.error(CodeSnippetAccessError.Error.RESTRICTION_ACCESS);
            }
        };
    }

    private RestrictionsCheckResult checkRestrictions(CodeSnippetEntity entity) {
        RestrictionsCheckResult result = RestrictionsCheckResult.ACCESS_ALLOWED;
        Integer leftViews = entity.getLeftViews();
        if (leftViews != null) {
            if (leftViews > 0) {
                entity.setLeftViews(leftViews - 1);
                result = RestrictionsCheckResult.ENTITY_UPDATE_REQUIRED;
            } else {
                result = RestrictionsCheckResult.ACCESS_DENIED;
            }
        }
        if (entity.getAvailableUntilDate() != null
                && entity.getAvailableUntilDate().isBefore(LocalDateTime.now(ZoneOffset.UTC))) {
            result = RestrictionsCheckResult.ACCESS_DENIED;
        }
        return result;
    }

    private enum RestrictionsCheckResult {
        ACCESS_ALLOWED,
        ENTITY_UPDATE_REQUIRED,
        ACCESS_DENIED,
    }

    public List<CodeSnippet> get10LatestUploadedCodeSnippets() {
        return codeSnippetRepository.findTop10ByLeftViewsIsNullAndAvailableUntilDateIsNullOrderByCreationDateDesc()
                .stream()
                .map(codeSnippetMapper::toModel)
                .collect(Collectors.toList());
    }
}
