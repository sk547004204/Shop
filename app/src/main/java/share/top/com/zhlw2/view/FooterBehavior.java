package share.top.com.zhlw2.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZHOU on 2016/4/20.
 */
public class FooterBehavior extends FloatingActionButton.Behavior {

    public FooterBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        //开始滑动监听
        return nestedScrollAxes == ViewGroup.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //滑动过程中不断的调用该方法
        //判断方向
        if (dyConsumed > 0) {
            //滑进来
            animationIn(child);
        } else if (dyConsumed < 0) {
            //滑出去
            animationOut(child);
        } else {
        }
    }

    private void animationIn(FloatingActionButton child) {
        ViewCompat.animate(child).translationX(child.getWidth()).start();
    }

    private void animationOut(FloatingActionButton child) {
        ViewCompat.animate(child).translationX(0).start();
    }
}
