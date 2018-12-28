package com.example.a32672.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View searchBar;
    private EditText editText;
    private ImageView queryButton;
    private MingRecyclerView recyclerView;
    private List<MainItemBean> data;
    int mFirstY, mCurrentY, marginTop;
    boolean direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        data = new ArrayList<>();
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "头条"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "社会"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "国内"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "国际"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "娱乐"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "体育"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "军事"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "科技"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "财经"));
        data.add(new MainItemBean(R.drawable.ic_launcher_background, "时尚"));

        initView();
    }
    private void initView() {
        marginTop = DisplayUtil.dp2px(this, 85); //顶部搜索栏高度为85dp，转为像素值255
        MainListAdapter adapter = new MainListAdapter(this, data);
        adapter.setOnItemClickListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        searchBar = findViewById(R.id.search_bar);
        editText=findViewById(R.id.exit_text);
        queryButton=findViewById(R.id.query_button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString();

            }
        });

        recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mFirstY=recyclerView.getTouchPointY();
                        mCurrentY = (int) motionEvent.getY();
                        Log.d("ttt","mFirstY:"+mFirstY+" mCurrentY:"+mCurrentY);
                        RelativeLayout.LayoutParams top = (RelativeLayout.LayoutParams) searchBar.getLayoutParams();
                        if (mCurrentY - mFirstY > 0) {
                            direction = false; //向下滑动
                        } else {
                            direction = true; //向上滑动
                        }
                        Log.d("uuu","top.margin:"+top.topMargin+" direction:"+direction);
                        if (direction) {
                            Log.d("uuu","上滑");
                            if (top.topMargin > -marginTop) {
                                top.topMargin += mCurrentY - mFirstY;
                                if(top.topMargin<-marginTop)
                                    top.topMargin=-marginTop;
                                Log.d("uuu","top2:"+top.topMargin);
                                searchBar.requestLayout();
                            }
                        } else {
                            if (top.topMargin < 0) {
                                top.topMargin += mCurrentY - mFirstY;
                                if(top.topMargin>0)top.topMargin=0;
                                searchBar.requestLayout();
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }
}
