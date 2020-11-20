package com.example.resturantfinder.utils;

import com.example.resturantfinder.model.Restaurant;

public interface MainAcitivtyListner {
    void click(Restaurant restaurant);
    void delFromFav(Restaurant restaurant);
    void restaurantDetails(Restaurant restaurant);
}
