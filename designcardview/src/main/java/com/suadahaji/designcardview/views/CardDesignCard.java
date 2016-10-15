package com.suadahaji.designcardview.views;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class CardDesignCard {
  public String title;
  public Drawable imageResource;
  @StringRes
  public int titleResourceId;
  @DrawableRes
  public int imageResourceId;
  @ColorRes
  public int titleColor;
  @ColorRes
  public int backgroundColor;

  public float titleTextSize;
  public float descriptionTextSize;

  public CardDesignCard(String title) {
    this.title = title;
  }

  public CardDesignCard(String title, int imageResourceId) {
    this.title = title;
    this.imageResourceId = imageResourceId;
  }

  public CardDesignCard(String title, Drawable imageResource) {
    this.title = title;
    this.imageResource = imageResource;
  }


  public CardDesignCard(int title, int imageResourceId) {
    this.titleResourceId = title;
    this.imageResourceId = imageResourceId;
  }

  public CardDesignCard(int title, Drawable imageResource) {
    this.titleResourceId = title;
    this.imageResource = imageResource;
  }

  public String getTitle() {
    return title;
  }

  public int getTitleResourceId() {
    return titleResourceId;
  }

  public int getTitleColor() {
    return titleColor;
  }

  public void setTitleColor(int color) {
    this.titleColor = color;
  }

  public void setImageResourceId(int imageResourceId) {
    this.imageResourceId = imageResourceId;
  }

  public int getImageResourceId() {
    return imageResourceId;
  }

  public float getTitleTextSize() {
    return titleTextSize;
  }

  public void setTitleTextSize(float titleTextSize) {
    this.titleTextSize = titleTextSize;
  }

  public float getDescriptionTextSize() {
    return descriptionTextSize;
  }

  public int getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(int backgroundColor) {
    this.backgroundColor = backgroundColor;
  }
}
