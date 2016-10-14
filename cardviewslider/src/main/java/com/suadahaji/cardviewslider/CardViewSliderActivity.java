package com.suadahaji.cardviewslider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.suadahaji.cardviewslider.helper.CardTransformer;
import com.suadahaji.cardviewslider.helper.CircleView;
import com.suadahaji.cardviewslider.views.CardDesignCard;
import com.suadahaji.cardviewslider.views.CardDesignFragment;

import java.util.List;

public class CardViewSliderActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
  private CircleView circleView;
  private ViewPager cardViewPager;
  private CardDesignAdapter cardViewAdapter;
  private RelativeLayout parentLayout;
  private Toolbar toolbar;

  private CardTransformer mCardTransformer;
  private Typeface typeface;
  private List<CardDesignCard> pages;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
    setContentView(R.layout.activity_card_view_slider);

    setStatusBackgroundColor();
    toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
    hideActionBar();
    toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);


    parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);
    circleView = (CircleView) findViewById(R.id.circle_indicator_view);
    cardViewPager = (ViewPager) findViewById(R.id.vp_pager);
    cardViewPager.addOnPageChangeListener(this);

  }

  public void setCardViewPages(List<CardDesignCard> pages) {

    this.pages = pages;
    cardViewAdapter = new CardDesignAdapter(pages, getSupportFragmentManager(), dpToPixels(0, this), typeface);
    mCardTransformer = new CardTransformer(cardViewPager, cardViewAdapter);
    mCardTransformer.enableScaling(true);
    cardViewPager.setAdapter(cardViewAdapter);
    cardViewPager.setPageTransformer(false, mCardTransformer);
    circleView.setPageIndicators(pages.size());
  }

  private float dpToPixels(int dp, Context context) {
    return dp * (context.getResources().getDisplayMetrics().density);
  }

  private void setStatusBackgroundColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().getDecorView().setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
      getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_transparent));
    }
  }

  @Override
  public void onClick(View v) {
    boolean onFirstPage = cardViewPager.getCurrentItem() == 0;
    boolean onLastPage = cardViewPager.getCurrentItem() == cardViewAdapter.getCount() - 1;

    if (!onFirstPage) {
      cardViewPager.setCurrentItem(cardViewPager.getCurrentItem() - 1);
    } else if (!onLastPage) {
      cardViewPager.setCurrentItem(cardViewPager.getCurrentItem() + 1);
    }
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {

    CardDesignFragment fragment = (CardDesignFragment) cardViewAdapter.getItem(position);
    fragment.getImageView();
    setGradientBackground(fragment.getImageView().getDrawable());

    circleView.setCurrentPage(position);
  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  private void hideActionBar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  public void setGradientBackground(Drawable drawable) {


    Bitmap image = drawableToBitmap(drawable);
    Palette.from(image).generate(new Palette.PaletteAsyncListener() {
      @Override
      public void onGenerated(Palette palette) {
        Palette.Swatch vibrantSwach = palette.getDarkMutedSwatch();
        if (vibrantSwach != null) {
          parentLayout.setBackgroundColor(vibrantSwach.getRgb());
        }
      }
    });
  }

  public Bitmap drawableToBitmap (Drawable drawable) {
    Bitmap bitmap;

    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
      if(bitmapDrawable.getBitmap() != null) {
        return bitmapDrawable.getBitmap();
      }
    }

    if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
      bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    } else {
      bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    }

    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);
    return bitmap;
  }

  public void setFont(Typeface typeface) {
    this.typeface = typeface;
  }
}
