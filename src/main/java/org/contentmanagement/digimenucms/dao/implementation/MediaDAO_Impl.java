package org.contentmanagement.digimenucms.dao.implementation;

import java.util.List;
import java.util.Optional;
import org.contentmanagement.digimenucms.dao.MediaDAO;
import org.contentmanagement.digimenucms.entity.Media;
import org.contentmanagement.digimenucms.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MediaDAO_Impl implements MediaDAO {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Optional<Media> saveMedia(Media media) {
       return Optional.of(mediaRepository.save(media));
    }

    @Override
    public Optional<Media> findById(String id) {
        return mediaRepository.findById(id);
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

}
