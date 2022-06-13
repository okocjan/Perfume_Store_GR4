package com.example.pzespolowe.Dto;

import org.springframework.web.multipart.MultipartFile;

public class SaveProductDto {
    private String name;
    private Integer capacity;
    private Double price;
    private String type;
    private Integer quantity;
    private String description;
    private MultipartFile multipartFile;

    public SaveProductDto() {
    }

    public SaveProductDto(String name, Integer capacity, Double price, String type, Integer quantity,
                          String description, MultipartFile multipartFile) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.multipartFile = multipartFile;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nname = " + name  +
                ", capacity = " + capacity +
                ", price = " + price +
                ", type = " + type +
                ", quantity = " + quantity +
                ", description = " + description +
                ", file = " + multipartFile.getOriginalFilename() + "\n";
    }
}
