package com.todo.todobackend.controllers;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.service.CategoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/id")
    public Category findById() {
        return categoryService.findById(60629L);
    }

    @PostMapping("/all")
    public List<Category> findAll(@RequestBody String email){
        return categoryService.findAll(email);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null && category.getId()!=0){
            return new ResponseEntity("ID Must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length()==0){
            return new ResponseEntity("Mussed Title Param3", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.add(category)); //возвращает добавленный объект
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category){
        if (category.getId() == null && category.getId()== 0){
            return new ResponseEntity("ID dont Must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length()==0){
            return new ResponseEntity("Mussed Title Param8", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryService.update(category);
        return new ResponseEntity(HttpStatus.OK); //возвращаем только статус 200ок
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)  {
       try {
            categoryService.deleteById(id);
       } catch (EmptyResultDataAccessException e) {
           e.printStackTrace();
           return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);

       }
       return new ResponseEntity("Category with id = "+ id+ " deleted", HttpStatus.OK);
    }
}
