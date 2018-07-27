package br.com.jmsstudio.supply.model;

public class Product {
    private String name;
    private double value;
    private String brand;

    public Product(String name, double value, String brand) {
        this.name = name;
        this.value = value;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", value=" + value + ", brand=" + brand + "]";
    }
}
