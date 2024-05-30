package com.sage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sage.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
