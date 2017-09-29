package dimon.lightforrelay;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LightRecyclerViewAdapter.OnItemClickLister, LightRecyclerViewAdapter.onRecyclerItemLongClickLister {
    private static final String TAG = "MainActivity";
    private RecyclerView cler;
    private String[] data;
    private LightRecyclerViewAdapter adapter;
    private RelayApplication application;
    private List<Light> lights = new ArrayList<>();
    private List<String> light_name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (RelayApplication) getApplication();
        initView();
    }


    void initView() {
        data = getResources().getStringArray(R.array.relay_name);
        light_name.clear();
        light_name.addAll(application.pref.getNameList());
        if (light_name.size() <= 0) {
            for (int i = 0; i < data.length; i++) {
                light_name.add(data[i]);
            }
        }
        if (application.pref.getFirstIn()) {
            Light light0 = new Light(4, light_name.get(0), false, data[0]);
            lights.add(0, light0);

            Light light1 = new Light(5, light_name.get(1), false, data[1]);
            lights.add(1, light1);

            Light light2 = new Light(1, light_name.get(2), false, data[2]);
            lights.add(2, light2);

            Light light3 = new Light(2, light_name.get(3), false, data[3]);
            lights.add(3, light3);

            Light light4 = new Light(3, light_name.get(4), false, data[4]);
            lights.add(4, light4);
        } else {
            Light light0 = new Light(4, light_name.get(0), application.pref.getGHFlag(), data[0]);
            lights.add(0, light0);

            Light light1 = new Light(5, light_name.get(1), application.pref.getGMFlag(), data[1]);
            lights.add(1, light1);

            Light light2 = new Light(1, light_name.get(2), application.pref.getGLFlag(), data[2]);
            lights.add(2, light2);

            Light light3 = new Light(2, light_name.get(3), application.pref.getCCFlag(), data[3]);
            lights.add(3, light3);

            Light light4 = new Light(3, light_name.get(4), application.pref.getCHFlag(), data[4]);
            lights.add(4, light4);
        }

        //开机时，判断上次继电器开启状态，并开启继电器,因为每次关闭电源，继电器都会断开
        for (int i = 0; i < lights.size(); i++) {
            if (lights.get(i).getOpen()) {
                application.deviceData.JNI_SetGpio(lights.get(i).getId(), RelayApplication.ON);
            }
        }

        cler = (RecyclerView) findViewById(R.id.recycle_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        cler.setLayoutManager(layoutManager);
        cler.setHasFixedSize(true);
        adapter = new LightRecyclerViewAdapter(this, lights);
        cler.setAdapter(adapter);
        adapter.setReOnItemClickListener(this);
        adapter.setReOnItemLongClickListener(this);
    }

    @Override
    public void setRecyclerItemClickLister(View v, int position) {
        switchRelay(position, lights.get(position).getOpen());
    }

    public void switchRelay(int position, boolean open) {
        int on_off;
        if (open) {
            on_off = RelayApplication.OFF;
        } else {
            on_off = RelayApplication.ON;
        }

        switch (position) {
            case 0:
                application.pref.setGHFlag(!open);
                break;

            case 1:
                application.pref.setGMFlag(!open);
                break;
            case 2:
                application.pref.setGLFlag(!open);
                break;
            case 3:
                application.pref.setCCFlag(!open);
                break;
            case 4:
                application.pref.setCHFlag(!open);
                break;
        }
        application.deviceData.JNI_SetGpio(lights.get(position).getId(), on_off);
        lights.get(position).setOpen(!open);
        adapter.notifyItemChanged(position);

    }

    @Override
    public boolean setRecyclerItemLongClickListener(View v, final int position) {
        final EditText editText = new EditText(this);

        new AlertDialog.Builder(this).setTitle("请输入")
                .setView(editText)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        light_name.add(position, editText.getText().toString());
                        application.pref.setNameList(light_name);
                        lights.get(position).setName(editText.getText().toString());
                        adapter.notifyItemChanged(position);
                    }
                }).show();

        return false;
    }
}
