package com.example.testicst;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Intro extends AppIntro2{

    int darkGrey = ContextCompat.getColor(this, R.color.darkGrey);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPage introWelcome = new SliderPage();
        introWelcome.setTitle("Welcome!");
        introWelcome.setDescription("something about our app");
        introWelcome.setImageDrawable(R.drawable.cutelogo);
        introWelcome.setBgColor(Color.WHITE);
        introWelcome.setTitleColor(Color.DKGRAY);
        introWelcome.setDescColor(Color.DKGRAY);
        addSlide(AppIntro2Fragment.newInstance(introWelcome));

        SliderPage introTest = new SliderPage();
        introTest.setTitle("Pass the test!");
        introTest.setDescription("test description and profit");
        introTest.setImageDrawable(R.drawable.cutelogo);
        introTest.setBgColor(Color.DKGRAY);
        introTest.setTitleColor(Color.DKGRAY);
        introTest.setDescColor(Color.DKGRAY);
        addSlide(AppIntro2Fragment.newInstance(introTest));

        SliderPage introBook = new SliderPage();
        introBook.setTitle("Read the book!");
        introBook.setDescription("what you can find in the book");
        introBook.setImageDrawable(R.drawable.cutelogo);
        introBook.setBgColor(Color.WHITE);
        introBook.setTitleColor(Color.DKGRAY);
        introBook.setDescColor(Color.DKGRAY);
        addSlide(AppIntro2Fragment.newInstance(introBook));

        SliderPage introAbout = new SliderPage();
        introAbout.setTitle("Go to ICST!");
        introAbout.setDescription("something about ICST");
        introAbout.setImageDrawable(R.drawable.cutelogo);
        introAbout.setBgColor(Color.WHITE);
        introAbout.setTitleColor(Color.DKGRAY);
        introAbout.setDescColor(Color.DKGRAY);
        addSlide(AppIntro2Fragment.newInstance(introAbout));

        showSkipButton(false);

    }
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        //Intent intent = new Intent(Intro.this, MainActivity.class);
        //    startActivity(intent);
    }
}




