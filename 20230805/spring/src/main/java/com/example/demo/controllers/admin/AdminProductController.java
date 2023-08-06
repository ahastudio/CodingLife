package com.example.demo.controllers.admin;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.CreateProductService;
import com.example.demo.application.GetProductDetailService;
import com.example.demo.application.GetProductListService;
import com.example.demo.application.UpdateProductService;
import com.example.demo.dtos.admin.AdminCreateProductDto;
import com.example.demo.dtos.admin.AdminProductDetailDto;
import com.example.demo.dtos.admin.AdminProductListDto;
import com.example.demo.dtos.admin.AdminUpdateProductDto;
import com.example.demo.models.CategoryId;
import com.example.demo.models.Image;
import com.example.demo.models.ImageId;
import com.example.demo.models.Money;
import com.example.demo.models.ProductId;
import com.example.demo.models.ProductOption;
import com.example.demo.models.ProductOptionId;
import com.example.demo.models.ProductOptionItem;
import com.example.demo.models.ProductOptionItemId;
import com.example.demo.security.AdminRequired;

@AdminRequired
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    private final GetProductListService getProductListService;
    private final GetProductDetailService getProductDetailService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;

    public AdminProductController(
            GetProductListService getProductListService,
            GetProductDetailService getProductDetailService,
            CreateProductService createProductService,
            UpdateProductService updateProductService) {
        this.getProductListService = getProductListService;
        this.getProductDetailService = getProductDetailService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
    }

    @GetMapping
    public AdminProductListDto list() {
        return getProductListService.getAdminProductListDto();
    }

    @GetMapping("/{id}")
    public AdminProductDetailDto detail(@PathVariable String id) {
        ProductId productId = new ProductId(id);
        return getProductDetailService.getAdminProductDetailDto(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody AdminCreateProductDto productDto) {
        createProductService.createProduct(
                new CategoryId(productDto.categoryId()),
                mapToImages(productDto.images()),
                productDto.name(),
                new Money(productDto.price()),
                mapToProductOptions(productDto.options()),
                productDto.description()
        );
        return "Created";
    }

    @PatchMapping("/{id}")
    public String update(
            @PathVariable String id,
            @Valid @RequestBody AdminUpdateProductDto productDto) {
        updateProductService.updateProduct(new ProductId(id), productDto);
        return "Updated";
    }

    private List<Image> mapToImages(
            List<AdminCreateProductDto.ImageDto> imageDtos) {
        return imageDtos.stream()
                .map(image -> new Image(ImageId.generate(), image.url()))
                .toList();
    }

    private List<ProductOption> mapToProductOptions(
            List<AdminCreateProductDto.OptionDto> optionDtos) {
        return optionDtos.stream()
                .map(option -> new ProductOption(
                        ProductOptionId.generate(),
                        option.name(),
                        mapToProductOptionItems(option.items())
                ))
                .toList();
    }

    private List<ProductOptionItem> mapToProductOptionItems(
            List<AdminCreateProductDto.OptionItemDto> optionItemDtos) {
        return optionItemDtos.stream()
                .map(optionItem -> new ProductOptionItem(
                        ProductOptionItemId.generate(),
                        optionItem.name()
                ))
                .toList();
    }
}
