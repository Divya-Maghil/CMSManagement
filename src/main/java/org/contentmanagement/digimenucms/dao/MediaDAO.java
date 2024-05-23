package org.contentmanagement.digimenucms.dao;

import java.util.List;
import java.util.Optional;
import org.contentmanagement.digimenucms.entity.Media;

public interface MediaDAO {

    Optional<Media> saveMedia(Media media);

    Optional<Media> findById(String id);

    List<Media> getAllMedia();
}
