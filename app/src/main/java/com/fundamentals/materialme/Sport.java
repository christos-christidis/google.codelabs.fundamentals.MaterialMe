package com.fundamentals.materialme;

class Sport {

    private final String mTitle;
    private final String mInfo;
    private final int mImageResId;

    Sport(String title, String info, int imageResId) {
        mTitle = title;
        mInfo = info;
        mImageResId = imageResId;
    }

    String getTitle() {
        return mTitle;
    }

    String getInfo() {
        return mInfo;
    }

    int getImageResId() {
        return mImageResId;
    }
}
