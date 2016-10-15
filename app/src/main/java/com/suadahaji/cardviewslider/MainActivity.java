package com.suadahaji.cardviewslider;

import android.graphics.Typeface;
import android.os.Bundle;

import com.suadahaji.designcardview.CardViewSliderActivity;
import com.suadahaji.designcardview.views.CardDesignCard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CardViewSliderActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    showActionBar();


    CardDesignCard cardDesignCard1 = new CardDesignCard("Popular", R.drawable.captain);
    CardDesignCard cardDesignCard2 = new CardDesignCard("Top Rated", R.drawable.image_one);
    CardDesignCard cardDesignCard3 = new CardDesignCard("Upcoming", R.drawable.inferno);
    CardDesignCard cardDesignCard4 = new CardDesignCard("Now Playing", R.drawable.image_two);

    List<CardDesignCard> cardList = new ArrayList<>();
    cardList.add(cardDesignCard1);
    cardList.add(cardDesignCard2);
    cardList.add(cardDesignCard3);
    cardList.add(cardDesignCard4);

    Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
    setFont(face);

    setCardViewPages(cardList);
  }
}
