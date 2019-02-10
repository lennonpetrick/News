package com.test.news.presentation.base;

public interface BaseContract {

    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
    }

    interface Presenter<T extends View> {
        void setView(T view);
        void load();
        void destroy();
    }

}
