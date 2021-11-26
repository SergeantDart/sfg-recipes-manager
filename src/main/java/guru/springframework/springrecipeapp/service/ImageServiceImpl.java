package guru.springframework.springrecipeapp.service;

import guru.springframework.springrecipeapp.model.Recipe;
import guru.springframework.springrecipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(long id, MultipartFile multipartFile) {
        log.debug("Saving the image...");

        try  {
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] bytes = new Byte[multipartFile.getBytes().length];
            int i = 0;
            for(byte b : multipartFile.getBytes()) {
                bytes[i++] = b;
            }
            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
