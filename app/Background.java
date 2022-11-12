import android.os.AsyncTask;

import java.net.URL;
import java.net.MalformedURLException;

public class Background extends AsyncTask <String, Void, String>{

    @Override
    protected String doInBackground(String... voids) {
        String result ="";
        String user = voids[0];
        String pass = voids[1];
        return null;
    }
}
