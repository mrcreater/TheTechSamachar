package parin_patel.example.com.thetechsamachartts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by parin_patel on 10/4/2017.
 */

public class Day_night extends AppCompatActivity {
    Switch daynight;
    View bgview;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.nightmode);
        daynight = (Switch) findViewById(R.id.daynight);


        daynight.animate();
        daynight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(Day_night.this, "Night Mode", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Day_night.this, "Day Mode", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}