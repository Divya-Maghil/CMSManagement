package org.contentmanagement.digimenucms.service;

import java.util.List;
import org.contentmanagement.digimenucms.dto.RequestDTO;
import org.contentmanagement.digimenucms.error.AwsImageUploadFailedException;
import org.contentmanagement.digimenucms.error.ImageNotFoundException;
import org.contentmanagement.digimenucms.error.MediaNotFoundException;
import org.springframework.http.ResponseEntity;

public interface MediaService {
    ResponseEntity<?> addorUpdateContent(List<RequestDTO> requestDTOS) throws MediaNotFoundException, AwsImageUploadFailedException, ImageNotFoundException;

    ResponseEntity<?> getAllMedia() throws MediaNotFoundException;
}
