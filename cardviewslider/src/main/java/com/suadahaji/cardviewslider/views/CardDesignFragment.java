package com.suadahaji.cardviewslider.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suadahaji.cardviewslider.R;

/**
 * Created by suadahaji.
 */

public class CardDesignFragment extends Fragment {
  private static final String PAGE_TITLE = "page_title";
  private static final String PAGE_TITLE_RES_ID = "page_title_res_id";
  private static final String PAGE_TITLE_COLOR = "page_title_color";
  private static final String PAGE_TITLE_TEXT_SIZE = "page_title_text_size";
  private static final String PAGE_IMAGE_RES_ID = "page_image_res_id";
  private static final String PAGE_BACKGROUND_COLOR = "page_background_color";

  private String title;
  @StringRes
  private int titleResId;
  @ColorRes
  private int titleColor;
  @ColorRes
  private int backgroundColor;
  @DrawableRes
  private int imageResId;
  private float titleTextSize;

  private View view;
  private ImageView cardViewImage;
  private TextView cardViewTitle;
  private CardView cardView;

  public CardDesignFragment() {
  }

  public static CardDesignFragment newInstance(CardDesignCard card) {
    Bundle args = new Bundle();
    args.putString(PAGE_TITLE, card.getTitle());
    args.putInt(PAGE_TITLE_RES_ID, card.getTitleResourceId());
    args.putInt(PAGE_TITLE_COLOR, card.getTitleColor());
    args.putInt(PAGE_IMAGE_RES_ID, card.getImageResourceId());
    args.putFloat(PAGE_TITLE_TEXT_SIZE, card.getTitleTextSize());
    args.putInt(PAGE_BACKGROUND_COLOR, card.getBackgroundColor());

    CardDesignFragment fragment = new CardDesignFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    Bundle bundle = getArguments();

    title = bundle.getString(PAGE_TITLE, null);
    titleResId = bundle.getInt(PAGE_TITLE_RES_ID, 0);
    titleColor = bundle.getInt(PAGE_TITLE_COLOR, 0);
    titleTextSize = bundle.getFloat(PAGE_TITLE_TEXT_SIZE, 0f);
    imageResId = bundle.getInt(PAGE_IMAGE_RES_ID, 0);
    backgroundColor = bundle.getInt(PAGE_BACKGROUND_COLOR, 0);

    view = inflater.inflate(R.layout.card_design_fragment, container, false);
    cardViewImage = (ImageView) view.findViewById(R.id.iv_image);
    cardViewImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    cardViewTitle = (TextView) view.findViewById(R.id.text_view_title);
    cardView = (CardView) view.findViewById(R.id.cv_cardview);
    cardView.setPreventCornerOverlap(false);

    if (title != null) {
      cardViewTitle.setText(title);
    }

    if (titleResId != 0) {
      cardViewTitle.setText(getResources().getString(titleResId));
    }

    if (imageResId != 0) {

      cardViewImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), imageResId));
    }

    if (titleTextSize != 0f) {
      cardViewTitle.setTextSize(titleTextSize);
    }

    if (backgroundColor != 0) {
      cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), backgroundColor));
    }

    return view;
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  public CardView getCardView() {
    return cardView;
  }

  public ImageView getImageView() {
    return cardViewImage;
  }



}
