package share.top.com.zhlw2.activity;


import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import share.top.com.zhlw2.MainActivity;
import share.top.com.zhlw2.R;

public class LogoActivity extends BaseActivity {

    private ImageView logoImage;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_logo);
    }

    @Override
    public void beforeView() {

    }

    @Override
    public void initView() {
        logoImage = (ImageView) findViewById(R.id.logoImage);
    }

    @Override
    public void afterView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_log_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(LogoActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logoImage.startAnimation(animation);
    }
}
