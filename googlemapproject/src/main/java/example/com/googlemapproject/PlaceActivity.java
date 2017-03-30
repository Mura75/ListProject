package example.com.googlemapproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceActivity extends AppCompatActivity {

    private static final int PLACE_PICKER = 112;

    @BindView(R.id.tvPlaceName)
    TextView tvPlaceName;

    @BindView(R.id.buttonChoosePlace)
    Button buttonChoosePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonChoosePlace)
    public void choosePlace() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            Log.d("google_place", e.toString());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            Log.d("google_place", e.toString());
        }

        //Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("google_place", data.toString());
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PLACE_PICKER) {
                Place place = PlacePicker.getPlace(this, data);
                tvPlaceName.setText(place.toString());
            }
        }
    }
}
