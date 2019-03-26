package net.lzzy.cinemanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.frament.CinemasFragment;
import net.lzzy.cinemanager.frament.OrderFragment;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager manager = getSupportFragmentManager();
    private LinearLayout layoutMenu;
    private TextView tvTitle;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setTitleMenu();
    }

    private void setTitleMenu() {
        layoutMenu = findViewById(R.id.bar_menu);
        layoutMenu.setVisibility(View.GONE);
        findViewById(R.id.bar_img_menu).setOnClickListener(v -> {
            int visible = layoutMenu.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            layoutMenu.setVisibility(visible);
        });
        tvTitle = findViewById(R.id.bar_title_tv_title);
        tvTitle.setText("我的订单");
        search = findViewById(R.id.bar_searchView);
        findViewById(R.id.bar_order).setOnClickListener(this);
        findViewById(R.id.bar_add_cinema).setOnClickListener(this);
        findViewById(R.id.bar_add_order).setOnClickListener(this);
        findViewById(R.id.bar_see_cinema).setOnClickListener(this);
        findViewById(R.id.bar_exit).setOnClickListener(v -> System.exit(0));
    }

    @Override
    public void onClick(View v) {
        layoutMenu.setVisibility(View.GONE);
        switch (v.getId()) {
            case R.id.bar_order:
                tvTitle.setText("我的订单");
                manager.beginTransaction().replace(R.id.fragment_container,
                        new OrderFragment())
                        .commit();
                break;
            case R.id.bar_add_order:
                break;
            case R.id.bar_add_cinema:
                finish();
                break;
            case R.id.bar_see_cinema:
                tvTitle.setText("影院列表");
                manager.beginTransaction().replace(R.id.fragment_container,
                        new CinemasFragment())
                        .commit();
                break;
            default:
                break;
        }
    }
}
