package org.contentmanagement.digimenucms.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {

    private String id;
    private String entityId;
    private String entityType;
    private String fileName;
    private String mimeType;
    private Integer sortOrder;
    private String tag;
    private String base64Image;

}
