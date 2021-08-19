package springone.messageboardadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminApiResponse {
    private String message;
    private String type;
    private String parameter;
}
