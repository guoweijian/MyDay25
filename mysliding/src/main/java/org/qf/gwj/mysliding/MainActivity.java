package org.qf.gwj.mysliding;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //将菜单加到activity，通过滑动显示菜单

    private SlidingMenu mSlidingMenu;

    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSlidingMenu();
        initView();
        getWindow().setBackgroundDrawableResource(R.mipmap.xiaopangzi);
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.clearCache);

        mButton.setOnClickListener(this);

    }


    private void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);

        //设置菜单模式， 从左滑出
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑动模式：从边界滑出
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置菜单
        mSlidingMenu.setMenu(R.layout.menu);
        //关联Activity，显示到activity
        mSlidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //菜单添加到activity（布局）
        //behind 对应 ------- 菜单
        mSlidingMenu.setBehindOffset(200);
        //滑动时视差的效果
        mSlidingMenu.setBehindScrollScale(0f);

        //对应菜单

          mSlidingMenu.setBehindCanvasTransformer(
                  new SlidingMenu.CanvasTransformer() {
              @Override
              public void transformCanvas(Canvas canvas,
                                          float percentOpen) {
                  //参数一代表画布，canvas决定菜单滑出的时候，怎么样绘制
                  //参数二打开的百分比

                  float scale = 0.5f+percentOpen;

                  canvas.scale(scale,scale,
                          canvas.getWidth()/2,canvas.getHeight()/2);


                  canvas.rotate(360*percentOpen,canvas.getWidth()/4
                          ,canvas.getHeight()/4);
              }
          });
        //activity 布局界面加动画
        mSlidingMenu.setAboveCanvasTransformer
                (new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas,
                                        float percentOpen) {

                float scale = 1 - 0.5f*percentOpen;

                canvas.scale(scale,scale,0,canvas.getHeight()/2);

            }
        });




    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "清除数据", Toast.LENGTH_SHORT).show();
        //关闭菜单
        mSlidingMenu.toggle();
    }

    public void show(View view) {
        mSlidingMenu.toggle();
    }
}
