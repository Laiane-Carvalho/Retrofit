package testeadapt3.cursoandroid2.com.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by laianeoliveira on 13/07/18.
 */

public class Change {
    String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;

    }

    public interface GuerritAPI {

        @GET("changes/")
        Call <List <Change>> loadChanges(@Query("q") String status);
    }
}
