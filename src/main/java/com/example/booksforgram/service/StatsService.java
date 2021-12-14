package com.example.booksforgram.service;

import com.example.booksforgram.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();

}