package org.contentmanagement.digimenucms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mh_media")
public class Media {

    @Id
    private String id;
    private String entityId;
    private String entityType;
    private String fileName;
    private String mimeType;
    private Integer sortOrder;
    private String tag;
}
