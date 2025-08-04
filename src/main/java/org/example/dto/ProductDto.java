package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 3, max = 50, message = "Tên phải từ 3 đến 50 ký tự")
    private String name;

    @Min(value = 0, message = "Giá không được nhỏ hơn 0")
    private double price;

    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
    private int stock;
}
