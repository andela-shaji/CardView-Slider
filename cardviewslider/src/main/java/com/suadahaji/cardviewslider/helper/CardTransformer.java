package com.suadahaji.cardviewslider.helper;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

public class CardTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer{
  private ViewPager mViewPager;
  private CardAdapter mAdapter;
  private float mLastOffset;
  private boolean mScalingEnabled;

  public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 6;
    float getBaseElevation();
    CardView getCardViewAt(int position);
    int getCount();
  }

  public CardTransformer(ViewPager viewPager, CardAdapter adapter) {
    mViewPager = viewPager;
    viewPager.addOnPageChangeListener(this);
    mAdapter = adapter;
  }

  public void enableScaling(boolean enable) {
    if (mScalingEnabled && !enable) {
      CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
      if (currentCard != null) {
        currentCard.animate().scaleY(0.5f);
        currentCard.animate().scaleX(0.5f);
      }
    }else if(!mScalingEnabled && enable){
      CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
      if (currentCard != null) {
        currentCard.animate().scaleY(1.1f);
        currentCard.animate().scaleX(1.1f);
      }
    }
    mScalingEnabled = enable;
  }

  @Override
  public void transformPage(View page, float position) {

  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    int realCurrentPosition;
    int nextPosition;
    float baseElevation = mAdapter.getBaseElevation();
    float realOffset;
    boolean goingLeft = mLastOffset > positionOffset;

    if (goingLeft) {
      realCurrentPosition = position + 1;
      nextPosition = position;
      realOffset = 1 - positionOffset;
    } else {
      nextPosition = position + 1;
      realCurrentPosition = position;
      realOffset = positionOffset;
    }

    // To avoid crash on over scrolling
    if (nextPosition > mAdapter.getCount() - 1
            || realCurrentPosition > mAdapter.getCount() - 1) {
      return;
    }

    CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);


    if (currentCard != null) {
      if (mScalingEnabled) {
        currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
        currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
      }
      currentCard.setCardElevation((baseElevation + baseElevation
              * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
    }

    CardView nextCard = mAdapter.getCardViewAt(nextPosition);

    if (nextCard != null) {
      if (mScalingEnabled) {
        nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
        nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
      }
      nextCard.setCardElevation((baseElevation + baseElevation
              * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
    }

    mLastOffset = positionOffset;
  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }
}
