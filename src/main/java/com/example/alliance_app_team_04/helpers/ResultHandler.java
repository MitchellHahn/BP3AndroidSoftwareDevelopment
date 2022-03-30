package com.example.alliance_app_team_04.helpers;

interface ResultHandler<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}
