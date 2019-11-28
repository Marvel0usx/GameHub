package com.example.userinterface.GameManager.HangMan;

import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

public class Character implements Serializable {

    private ImageView characterImage;

    Character(ImageView charImage) {
        this.characterImage = charImage;
        this.characterImage.setVisibility(View.VISIBLE);
    }


}

