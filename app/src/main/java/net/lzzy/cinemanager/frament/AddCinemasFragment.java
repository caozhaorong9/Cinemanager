package net.lzzy.cinemanager.frament;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityPicker;

import net.lzzy.cinemanager.R;
import net.lzzy.cinemanager.models.Cinema;

/**
 * @author lzzy_gxy
 * @date 2019/3/27
 * Description:
 */
public class AddCinemasFragment extends BaseFragment {

    private TextView tvArea;
    private EditText editText;
    private String city = "柳州市";
    private String province = "广西壮族自治区";
    private String area = "鱼峰区";
    private OnFragmentInteractionListener listener;
    private OnCinemaCreatedListener cinemaListener;
    //声明对象

    @Override
    protected void populate() {
        listener.hidSearch();
        initView();
        showDialog();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            listener.hidSearch();
        }

    }

    private void initView() {
        tvArea = findViewById(R.id.activity_dialog_location);
        editText = findViewById(R.id.activity_dialog_edt_name);
    }

    private void showDialog() {

        findViewById(R.id.activity_cinema_content_layoutArea).setOnClickListener(v -> {
            JDCityPicker cityPicker = new JDCityPicker();
            cityPicker.init(getActivity());
            cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                @Override
                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                    AddCinemasFragment.this.province = province.getName();
                    AddCinemasFragment.this.city = city.getName();
                    AddCinemasFragment.this.area = district.getName();
                    String loc = province.getName() + city.getName() + district.getName();
                    tvArea.setText(loc);
                }

                @Override
                public void onCancel() {
                }
            });
            cityPicker.showCityPicker();
        });
        findViewById(R.id.activity_dialog_save).setOnClickListener(v -> {
            Cinema cinema = new Cinema();
            String name = editText.getText().toString();
            String location=tvArea.getText().toString();
            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(location)) {
                Toast.makeText(getActivity(), "请输入影院信息", Toast.LENGTH_SHORT).show();
                return;
            }
            cinema.setCity(city);
            cinema.setName(name);
            cinema.setArea(area);
            cinema.setProvince(province);
            cinema.setLocation(location);
            cinemaListener.saveCinema(cinema);
            editText.setText("");
            tvArea.setText("");

        });
        findViewById(R.id.activity_dialog_cancel).setOnClickListener(v -> {
            cinemaListener.cancelAddCinema();


        });
    }

    @Override
    public int getLayout() {
        return R.layout.add_fragment_cinemas;
    }

    @Override
    public void search(String kw) {

    }

    /**
     * @param context=MainActivity 附加填充到activity
     *                             初始化
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            cinemaListener = (OnCinemaCreatedListener) context;
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "必需实现OnFragmentInteractionListener");
        }
    }

    /**
     * 销毁
     */
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        cinemaListener = null;
    }

    public interface OnCinemaCreatedListener {
        /**
         *点击取消保存
         */
        void cancelAddCinema();
        /**
         *点击保存
         */
        void saveCinema(Cinema cinema);
    }

}
