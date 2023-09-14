package com.serkansahin.service;

import com.serkansahin.exception.ResourcesNotFoundException;
import com.serkansahin.model.Category;
import com.serkansahin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<Category> findAll() {
	List<Category> listCategories = categoryRepository.findAll();
	if (listCategories.size() > 0) {
	  return listCategories;
	} else {
	  return new ArrayList<Category>();
	}
  }

  public ResponseEntity<Category> findById(Long id) {
	Category category = categoryRepository.findById(id)
										  .orElseThrow(() -> new ResourcesNotFoundException("Category not found ID: " + id));
	return ResponseEntity.ok(category);
  }

  public Category createCategory(Category category) {
	return categoryRepository.save(category);
  }

  public ResponseEntity<Category> updateOne(Category categoryInfo) throws ResourcesNotFoundException {
	Category category = categoryRepository.findById(categoryInfo.getId()).orElseThrow(() -> new ResourcesNotFoundException("Category not found ID: " + categoryInfo.getId()));
	return ResponseEntity.ok(categoryRepository.save(category));
  }

  public String deleteOne(Long id) {
	Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Category not found ID: " + id));
	categoryRepository.deleteById(id);

	;
	return "Silme işlemi başarılı";
  }

  public List<Category> getCategoriesByName(String name) {
	return categoryRepository.getCategoriesByName(name);
  }

}