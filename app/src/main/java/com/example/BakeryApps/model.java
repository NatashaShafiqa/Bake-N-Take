package com.example.BakeryApps;

public class model
{
  String url,description,name,price;

    public model(String url, String description, String name, String price) {
        this.url = url;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }


    public model() {
    }

    public model(String url) {

        this.url = url;
    }

}
