package com.noisyminner.stepfit20;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noisyminner.articleview.ArticleView;
import com.noisyminner.articleview.model.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticleView.OnLinkClick {

    private ArticleView articleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleView = (ArticleView) findViewById(R.id.articleView);

        FirebaseDatabase.getInstance().getReference("articles/id1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ArticleModel> models = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String data = "";
                            int pos = 0;
                            int type = 0;
                            int width = 0;
                            int height = 0;
                            try {
                                data = snapshot.child("data").getValue(String.class);
                            } catch (Throwable ignore) {
                            }
                            try {
                                pos = snapshot.child("position").getValue(Integer.class);
                            } catch (Throwable ignore) {
                            }
                            try {
                                type = snapshot.child("type").getValue(Integer.class);
                            } catch (Throwable ignore) {
                            }
                            try {
                                width = snapshot.child("dimens/width").getValue(Integer.class);
                            } catch (Throwable ignore) {
                            }
                            try {
                                height = snapshot.child("dimens/height").getValue(Integer.class);
                            } catch (Throwable ignore) {
                            }
                            models.add(new ArticleModel(pos, type, data, width, height));
                        }
                        articleView.setData(models, MainActivity.this);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        if (databaseError != null) {
                            databaseError.getMessage();
                        }
                    }
                });
    }

    @Override
    public void onLinkClicked(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}
