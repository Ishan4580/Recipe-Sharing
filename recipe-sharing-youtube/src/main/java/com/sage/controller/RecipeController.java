package com.sage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sage.model.Recipe;
import com.sage.model.User;
import com.sage.service.RecipeService;
import com.sage.service.UserService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public Recipe createRecipe(@RequestBody Recipe recipe,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe,@PathVariable Long id) throws Exception {
		
		
		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}
	
	@GetMapping()
	public List<Recipe> getAllRecipe() throws Exception {
		
		
		List<Recipe> recipe = recipeService.findAllRecipe();
		return recipe;
	}
	
	
	@DeleteMapping("/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
		
		
		recipeService.deleteRecipe(recipeId);
		return "recipe deleted successfully";
	}
	
	@PutMapping("/{id}/like")
	public Recipe likeRecipe(@RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		Recipe updatedRecipe = recipeService.likeRecipe(id, user);
		return updatedRecipe;
	}
}
