package com.todo.todobackend.service;

import com.todo.todobackend.entity.Category;
import com.todo.todobackend.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category findById(Long id){
        return categoryRepository.findById(id).get(); //60132L
    }
}
