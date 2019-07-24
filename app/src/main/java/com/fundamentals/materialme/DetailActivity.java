package com.fundamentals.materialme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_IMAGE_RESOURCE = "key_image_resource";

    static Intent newIntent(Context context, Sport sport) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_TITLE, sport.getTitle());
        intent.putExtra(KEY_IMAGE_RESOURCE, sport.getImageResId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView sportsTitle = findViewById(R.id.titleDetail);
        sportsTitle.setText(getIntent().getStringExtra(KEY_TITLE));

        // SOS: It's a good idea to always use Glide w images. It's much more efficient, since it uses
        // a background thread & only loads just the size required so it doesn't waste any memory!
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);
        Glide.with(this).load(getIntent().getIntExtra(KEY_IMAGE_RESOURCE, 0))
                .into(sportsImage);
    }
}
