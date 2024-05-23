package org.contentmanagement.digimenucms.dto;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTO {

    private String id;
    private String entityId;
    private String entityType;
    private Integer sortOrder;
    @Lob
    private String base64Image;

}
