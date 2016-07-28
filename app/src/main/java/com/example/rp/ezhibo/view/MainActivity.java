package com.example.rp.ezhibo.view;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.fragment.FriendFragment;
import com.example.rp.ezhibo.fragment.SYFragment;

public class MainActivity extends AppCompatActivity {
    private ActionBar bar;
    private LinearLayout[]layouts={null,null,null,null};
    private FragmentManager mFragmentManager;//进行碎片的管理
    private SYFragment syFragment,syFragment2,syFragment3,syFragment4;//这里改成自己写的Fragment
    private FriendFragment mFriendFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        bar=getSupportActionBar();
        bar.hide();

        setContentView(R.layout.activity_main);
        //第一次上传
        /**
         * 初始化四个fragment
         * 初始化4个LinearLayout
         * 初始化 数组 layouts
         */
        mFragmentManager=getSupportFragmentManager();
        init();

        ((ImageView) layouts[0].getChildAt(0)).setEnabled(false);
        ((TextView) layouts[0].getChildAt(1)).setTextColor(getResources().getColor(R.color.presstest));
        mFragmentManager.beginTransaction().add(R.id.main_fragment,syFragment).commit();
    }

    private void init() {
       syFragment=new SYFragment();
         syFragment2=new SYFragment();
        mFriendFragment=new FriendFragment();
        syFragment4=new SYFragment();


        LinearLayout sylayout= (LinearLayout) findViewById(R.id.main_bottom_layout1);//首页
        LinearLayout htlayout= (LinearLayout) findViewById(R.id.main_bottom_layout2);//话题
        LinearLayout zpylayout= (LinearLayout) findViewById(R.id.main_bottom_layout3);//找朋友
        LinearLayout xxlayout= (LinearLayout) findViewById(R.id.main_bottom_layout4);//消息

        layouts=new LinearLayout[]{sylayout,htlayout,zpylayout,xxlayout};//初始化数组 方便点击,图片和文字变颜色

    }

    /**
     * 点击下面四个图片或文字进行 Fragment之间的跳转
     * @param view
     */
    public void MyClick(View view) {
        switch (view.getId())
        {
            case R.id.main_bottom_layout1:

                mFragmentManager.beginTransaction().replace(R.id.main_fragment,syFragment).commit();
                for (int i = 0; i < 4; i++) {
                    ImageView mainbottomimg= (ImageView) layouts[i].getChildAt(0);
                    mainbottomimg.setEnabled(true);
                   TextView mainbottomtext= (TextView) layouts[i].getChildAt(1);
                    mainbottomtext.setTextColor(getResources().getColor(R.color.nortest));
                }
                ((ImageView) layouts[0].getChildAt(0)).setEnabled(false);
                ((TextView) layouts[0].getChildAt(1)).setTextColor(getResources().getColor(R.color.presstest));
                break;

            case R.id.main_bottom_layout2:

                mFragmentManager.beginTransaction().replace(R.id.main_fragment,syFragment2).commit();
                for (int i = 0; i < 4; i++) {
                    ImageView mainbottomimg= (ImageView) layouts[i].getChildAt(0);
                    mainbottomimg.setEnabled(true);
                    TextView mainbottomtext= (TextView) layouts[i].getChildAt(1);
                    mainbottomtext.setTextColor(getResources().getColor(R.color.nortest));
                }
                ((ImageView) layouts[1].getChildAt(0)).setEnabled(false);
                ((TextView) layouts[1].getChildAt(1)).setTextColor(getResources().getColor(R.color.presstest));//0x5566FB
                break;

            case R.id.main_bottom_layout3:

                mFragmentManager.beginTransaction().replace(R.id.main_fragment,mFriendFragment).commit();
                for (int i = 0; i < 4; i++) {
                    ImageView mainbottomimg= (ImageView) layouts[i].getChildAt(0);
                    mainbottomimg.setEnabled(true);
                    TextView mainbottomtext= (TextView) layouts[i].getChildAt(1);
                    mainbottomtext.setTextColor(getResources().getColor(R.color.nortest));
                }
                ((ImageView) layouts[2].getChildAt(0)).setEnabled(false);
                ((TextView) layouts[2].getChildAt(1)).setTextColor(getResources().getColor(R.color.presstest));
                break;

            case R.id.main_bottom_layout4:

                mFragmentManager.beginTransaction().replace(R.id.main_fragment,syFragment4).commit();
                for (int i = 0; i < 4; i++) {
                    ImageView mainbottomimg= (ImageView) layouts[i].getChildAt(0);
                    mainbottomimg.setEnabled(true);
                    TextView mainbottomtext= (TextView) layouts[i].getChildAt(1);
                    mainbottomtext.setTextColor(getResources().getColor(R.color.nortest));
                }
                ((ImageView) layouts[3].getChildAt(0)).setEnabled(false);
                ((TextView) layouts[3].getChildAt(1)).setTextColor(getResources().getColor(R.color.presstest));
                break;
        }
    }
}
