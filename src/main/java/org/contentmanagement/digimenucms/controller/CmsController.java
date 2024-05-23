package org.contentmanagement.digimenucms.controller;


import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.contentmanagement.digimenucms.dto.RequestDTO;
import org.contentmanagement.digimenucms.error.AwsImageUploadFailedException;
import org.contentmanagement.digimenucms.error.ImageNotFoundException;
import org.contentmanagement.digimenucms.error.MediaNotFoundException;
import org.contentmanagement.digimenucms.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms")
@CrossOrigin("*")
@AllArgsConstructor
public class CmsController {


    private MediaService mediaService;


    @PostMapping("/upload")
    public ResponseEntity<?> addOrUpdateContent(@RequestBody List<RequestDTO> requestDTOS) throws MediaNotFoundException, ImageNotFoundException , AwsImageUploadFailedException, IOException {
        return mediaService.addorUpdateContent(requestDTOS);
    }


    @GetMapping("/allMedia")
    public ResponseEntity<?> getAllMedia()throws MediaNotFoundException{
        return mediaService.getAllMedia();
    }

}
