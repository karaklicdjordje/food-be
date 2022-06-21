//package com.example.demo.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.dto.ShoppingCartDTO;
//import com.example.demo.repositories.FoodRepository;
//import com.example.demo.repositories.ShoppingCartRepository;
//import com.example.demo.services.ShoppingCartService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@RestController
//@RequestMapping("/shoppingCarts")
//@Tag(name = "Shopping cart controller", description = "Endpoints for managed user's shopping cart.")
//public class ShoppingCartController {
//	
//	@Autowired
//	private ShoppingCartService shoppingCartService;
//	
//	@Autowired
//	private FoodRepository foodRepo;
//	
//	@Autowired
//	private ShoppingCartRepository shoppingCartrepo;
//	
//	@PutMapping("/{shoppingCartId}/{offerId}")
//	@Operation(summary = "Add offer to shopping cart", description = "Add offer to shopping cart",
//	responses = {
//			@ApiResponse(responseCode = "200", description = "Offer successfully added.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
//			@ApiResponse(responseCode = "404", description = "Offer couldn't add to cart.")
//	})
//	@Parameter(in = ParameterIn.PATH, name = "shoppingCartId", schema = @Schema(implementation = Long.class))
//	@Parameter(in = ParameterIn.PATH, name = "offerId", schema = @Schema(implementation = Long.class))
//	public ResponseEntity<String> addToCart(@PathVariable Long shoppingCartId, @PathVariable Long offerId) {
//		return new ResponseEntity<String>("Proizvod je dodat u korpu", HttpStatus.OK);
//	}
//	
//	
//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<?> createShoppingCart(@RequestBody  ShoppingCartDTO shoppingCartDto){
//		return new ResponseEntity<>(shoppingCartService.createShoppingCart(shoppingCartDto), HttpStatus.OK);
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<ShoppingCartDTO>> findAll(){
//		return new ResponseEntity<List<ShoppingCartDTO>>(shoppingCartService.findAll(), HttpStatus.OK);
//		}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<ShoppingCartDTO> findById(@PathVariable Long id) {
//		return new ResponseEntity<ShoppingCartDTO>(shoppingCartService.findByID(id), HttpStatus.OK);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCartDTO shoppingCartDto) {
//	
//		return null;
//	}
//	
//	@PutMapping("/{id}/items/{itemId}")
//	public ResponseEntity<ShoppingCartDTO> removeItemFromShoppingCart(@PathVariable Long id, @PathVariable Long itemId) {
//	
//		return null;
//	}
//
//}
