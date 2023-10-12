package com.example.dvd24.API;

import com.example.dvd24.Models.NewHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewApiResponse> {
    void onFetchData(List<NewHeadlines> list, String message);
    void  onError(String message);
}
