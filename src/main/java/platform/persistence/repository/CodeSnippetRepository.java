package platform.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.persistence.entity.CodeSnippetEntity;

import java.util.List;

@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippetEntity, String> {

    List<CodeSnippetEntity> findTop10ByLeftViewsIsNullAndAvailableUntilDateIsNullOrderByCreationDateDesc();

}
