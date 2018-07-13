package testeadapt3.cursoandroid2.com.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laianeoliveira on 13/07/18.
 */
public class Controller implements Callback <List <Change>> {
    static final String BASE_URL = "https://git.eclipse.org/r/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create( gson ) )
                .build();

        Change.GuerritAPI guerritAPI = retrofit.create( Change.GuerritAPI.class );

        Call <List <Change>> call = guerritAPI.loadChanges( "status:open" );
        call.enqueue( this );

    }

    @Override
    public void onResponse(Call <List <Change>> call, Response <List <Change>> response) {
        if (response.isSuccessful()) {
            List <Change> changeList = response.body();
            changeList.forEach(change -> System.out.print( change.subject ) );
        } else {
            System.out.print( response.errorBody() );
        }

    }

    @Override
    public void onFailure(Call <List <Change>> call, Throwable t) {

    }
}
