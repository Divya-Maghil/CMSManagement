package org.contentmanagement.digimenucms.service.implentataion;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.contentmanagement.digimenucms.config.Aws;
import org.contentmanagement.digimenucms.config.AwsCredentials;
import org.contentmanagement.digimenucms.dao.MediaDAO;
import org.contentmanagement.digimenucms.dto.RequestDTO;
import org.contentmanagement.digimenucms.dto.ResponseDTO;
import org.contentmanagement.digimenucms.entity.Media;
import org.contentmanagement.digimenucms.error.AwsImageUploadFailedException;
import org.contentmanagement.digimenucms.error.ImageNotFoundException;
import org.contentmanagement.digimenucms.error.MediaNotFoundException;
import org.contentmanagement.digimenucms.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {


    private AwsCredentials awsCredentials;
    private MediaDAO mediaDAO;


    @Override
    public ResponseEntity<?> addorUpdateContent(List<RequestDTO> requestDTOS) throws AwsImageUploadFailedException, ImageNotFoundException, MediaNotFoundException {
        for (RequestDTO requestDTO : requestDTOS) {
            String id = requestDTO.getId();
            if (id == null || id.isEmpty()) {
                id = UUID.randomUUID().toString();

                if (requestDTO.getBase64Image() != null && !requestDTO.getBase64Image().isEmpty()) {
                    byte[] image = Base64.getDecoder().decode(requestDTO.getBase64Image());
                    Tika tika = new Tika();
                    String mimiType = tika.detect(image);
                    Aws aws = new Aws();

                    if (aws.uploadToS3(id, image, mimiType, awsCredentials.getACCESS_KEY(), awsCredentials.getSECRET_KEY(), awsCredentials.getBUCKET_NAME())) {
                        Media media = new Media();
                        media.setId(id);
                        media.setEntityId(requestDTO.getEntityId());
                        media.setEntityType(requestDTO.getEntityType());
                        media.setFileName("cms_" + System.currentTimeMillis());
                        media.setTag(null);
                        media.setMimeType(mimiType);
                        media.setSortOrder(requestDTO.getSortOrder());
                        mediaDAO.saveMedia(media);
                    }
                } else {
                    throw new ImageNotFoundException("Image not present in request body");
                }
            } else {
                Optional<Media> media = mediaDAO.findById(id);
                log.info("Media {}", media);
                log.info("Id : " + id);
                if (media.isPresent()) {
                    if (requestDTO.getBase64Image() != null && !requestDTO.getBase64Image().isEmpty()) {
                        byte[] image = Base64.getDecoder().decode(requestDTO.getBase64Image());
                        Tika tika = new Tika();
                        String mimiType = tika.detect(image);
                        Aws aws = new Aws();

                        if (aws.uploadToS3(id, image, mimiType, awsCredentials.getACCESS_KEY(), awsCredentials.getSECRET_KEY(), awsCredentials.getBUCKET_NAME())) {
                            Media updateMedia = media.get();
                            updateMedia.setId(updateMedia.getId());
                            updateMedia.setSortOrder(requestDTO.getSortOrder());
                            updateMedia.setTag(updateMedia.getTag());
                            updateMedia.setEntityId(updateMedia.getEntityId());
                            updateMedia.setFileName(updateMedia.getFileName());
                            updateMedia.setMimeType(mimiType);

                            mediaDAO.saveMedia(updateMedia);
                        }
                    }else {
                        throw new ImageNotFoundException("Image not present in request body");
                    }
                }else {
                    throw new MediaNotFoundException("Media Id "+id+" not found");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    @Override
    public ResponseEntity<?> getAllMedia() throws MediaNotFoundException {

        List<Media> medias = mediaDAO.getAllMedia();
        if (medias.isEmpty()){
            throw new MediaNotFoundException("No Media Found");
        }
        List<ResponseDTO> responseDTOS = new ArrayList<>();

        Aws aws = new Aws();

        for(Media media : medias){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .id(media.getId())
                    .entityId(media.getEntityId())
                    .entityType(media.getEntityType())
                    .fileName(media.getFileName())
                    .tag(media.getTag())
                    .base64Image(aws.getImageUrl(awsCredentials.getACCESS_KEY(), awsCredentials.getSECRET_KEY(), media.getId() ,awsCredentials.getBUCKET_NAME()))
                    .mimeType(media.getMimeType())
                    .sortOrder(media.getSortOrder())
                    .build();
            responseDTOS.add(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }



}
