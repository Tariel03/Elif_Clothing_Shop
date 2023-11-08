package com.example.clothes.controllers;

import com.example.clothes.Photo.PhotoConfig;
import com.example.clothes.dto.request.ClothRequest;
import com.example.clothes.dto.response.ClothResponse;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Image;
import com.example.clothes.enums.Status;
import com.example.clothes.services.Impl.ClothesServiceImpl;
import com.example.clothes.services.Impl.ImagesServiceImpl;
import com.example.clothes.services.Repo.BrandService;
import com.example.clothes.services.Repo.ColorService;
import com.example.clothes.services.Repo.ImageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final BrandService brandService;
    private final ColorService colorService;
    private final ClothesServiceImpl clothesService;
    private final ImagesServiceImpl imagesService;
    PhotoConfig photoConfig = new PhotoConfig();
    @PostMapping("/create/clothes")
    public ResponseEntity<String> createClothes(@Valid @RequestBody ClothRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : errors) {
                errorList.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        clothesService.save(request);
        return ResponseEntity.ok("Created new instance of clothes");
    }
    @PutMapping("/clothes/set/brand/{id}")
    public ResponseEntity<String> setBrand(@RequestParam("brand") String brandName, @PathVariable("id") Long id){
        Brand brand = brandService.findByBrand(brandName);
        Clothes clothes = clothesService.findById(id);
        clothes.setBrand(brand);
        clothesService.save(clothes);
        return ResponseEntity.ok("Successfully set brand");
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>>findBrands(){
        return ResponseEntity.ok(brandService.findActiveBrands());
    }

    @PostMapping("/create/brand")
    public ResponseEntity<String> createBrand( String brand) {
        if(brand.isBlank() || brand.isEmpty()){
            return new ResponseEntity<>("Name of the brand can't be blank or/and empty", HttpStatus.BAD_REQUEST);
        }
        brandService.save(brand);
        return ResponseEntity.ok("Successfully created!");
    }
    @PostMapping("/create/color")
    public ResponseEntity<String>createColor(String color){
        if(color.isBlank() || color.isEmpty()){
            return new ResponseEntity<>("Name of the color can't be blank or/and empty", HttpStatus.BAD_REQUEST);
        }
        colorService.save(color);
        return ResponseEntity.ok("Successfully created!");
    }
    @PutMapping("/delete/brand/{id}")
    public ResponseEntity<String>deleteBrand(@PathVariable Long id){
        brandService.changeStatusToDeleted(id);
        return ResponseEntity.ok("Successfully deleted");
    }
    @PutMapping("/delete/color/{id}")
    public ResponseEntity<String>deleteColor(@PathVariable Long id){
        colorService.changeStatusToDeleted(id);
        return ResponseEntity.ok("Successfully deleted");
    }
    @PostMapping("add/Photo/{id}")
    public ResponseEntity<String>setPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id){
        Clothes clothes = clothesService.findById(id);
        String link = (photoConfig.getPath()+"/"+file.getOriginalFilename());
        Image image = Image.builder().imageLink(link).clothes(clothes).build();
        imagesService.save(image);
        clothes.getImages().add(image);
        clothesService.save(clothes);
        photoConfig.savePhoto(file);

        return ResponseEntity.ok("Success!");
    }


}




