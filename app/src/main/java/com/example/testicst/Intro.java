package com.example.testicst;

import com.github.paolorotolo.appintro.AppIntro2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class Intro extends AppIntro2{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment firstFragment = new FragmentIntroFirst();
        Fragment secondFragment = new FragmentIntroSecond();
        Fragment thirdFragment = new FragmentIntroThird();
        Fragment forthFragment = new FragmentIntroForth();

        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);
        addSlide(forthFragment);

        showSkipButton(false);

    }
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(Intro.this, MainActivity.class);
            startActivity(intent);
    }
}




