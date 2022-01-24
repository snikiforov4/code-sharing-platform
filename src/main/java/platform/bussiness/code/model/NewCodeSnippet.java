package platform.bussiness.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCodeSnippet {
    String code;
    long ttlInSeconds;
    int viewsLeft;
}
