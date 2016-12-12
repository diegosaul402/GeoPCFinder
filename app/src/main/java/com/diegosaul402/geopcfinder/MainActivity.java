package com.diegosaul402.geopcfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.diegosaul402.geopcfinder.api.GeoAPIClient;
import com.diegosaul402.geopcfinder.api.GeoAPIService;
import com.diegosaul402.geopcfinder.api.PostalCodes;
import com.diegosaul402.geopcfinder.fragment.DetailsFragment;
import com.diegosaul402.geopcfinder.fragment.DetailsFragmentListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public DetailsFragmentListener fragmentListener;

    GeoAPIClient geoAPIClient = new GeoAPIClient();
    GeoAPIService geoAPIService;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.editTextInput)
    EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        geoAPIService = geoAPIClient.getGeoAPIService();

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.setRetainInstance(true);

        fragmentListener = detailsFragment;
        fragmentListener.initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button)
    public void handleClick() {
        Call<PostalCodes> call = geoAPIService.ListPostalCodes("postalCodeSearchJSON", editTextInput.getText().toString(), "MX", "10", "diego_402");
        //Call<PostalCodes> call = geoAPIService.ListPostalTest();

        call.enqueue(new Callback<PostalCodes>() {
            @Override
            public void onResponse(Call<PostalCodes> call, Response<PostalCodes> response) {
                fragmentListener.clearList();
                PostalCodes postalResponse = response.body();
                int size = postalResponse.getPostalCodes().size();

                for (int i = 0; i < size; i++) {
                    fragmentListener.addToList(postalResponse.getPostalCodes().get(i));
                    Log.v("Colonia", postalResponse.getPostalCodes().get(i).getPlaceName());
                    Log.v("CP", postalResponse.getPostalCodes().get(i).getPostalCode());
                }
            }

            @Override
            public void onFailure(Call<PostalCodes> call, Throwable t) {
                Log.e("Error", "Something went wrong" + t.getMessage());
            }
        });
    }
}
