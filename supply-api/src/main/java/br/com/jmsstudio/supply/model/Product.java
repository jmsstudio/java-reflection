package br.com.jmsstudio.supply.model;

import br.com.jmsstudio.processor.converter.annotation.XmlTagName;

@XmlTagName("product")
public class Product {
    @XmlTagName("name")
    private String name;
    @XmlTagName("value")
    private double value;
    @XmlTagName("productBrand")
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
