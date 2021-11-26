package guru.springframework.springrecipeapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(long id, MultipartFile multipartFile);

}
