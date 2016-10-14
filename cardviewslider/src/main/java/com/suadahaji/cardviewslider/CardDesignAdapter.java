package com.suadahaji.cardviewslider;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.suadahaji.cardviewslider.helper.CardTransformer;
import com.suadahaji.cardviewslider.views.CardDesignCard;
import com.suadahaji.cardviewslider.views.CardDesignFragment;

import java.util.ArrayList;
import java.util.List;

public class CardDesignAdapter extends FragmentStatePagerAdapter implements CardTransformer.CardAdapter {
  List<CardDesignCard> pages = new ArrayList<>();
  private List<CardDesignFragment> mFragments = new ArrayList<>();
  private float mBaseElevation;
  private Typeface typeface;

  public CardDesignAdapter(List<CardDesignCard> pages, FragmentManager fm, float baseElevation, Typeface typeface) {
    super(fm);
    this.pages = pages;
    this.typeface = typeface;
    this.mBaseElevation = baseElevation;

    for (int i = 0; i < pages.size(); i++) {
      addCardDesignFragment(pages.get(i));
    }
  }

  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    Object fragment = super.instantiateItem(container, position);
    mFragments.set(position, (CardDesignFragment) fragment);
    return fragment;
  }

  @Override
  public float getBaseElevation() {
    return mBaseElevation;
  }

  @Override
  public CardView getCardViewAt(int position) {
    return mFragments.get(position).getCardView();
  }

  public void addCardDesignFragment(CardDesignCard page) {
    mFragments.add(CardDesignFragment.newInstance(page));
  }

  @Override
  public int getCount() {
    return pages.size();
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);

  }

}
