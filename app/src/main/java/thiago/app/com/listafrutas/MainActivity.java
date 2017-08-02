package thiago.app.com.listafrutas;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.R.id.content;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fruta> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LeArquivoJSON().execute("https://raw.githubusercontent.com/muxidev/desafio-android/master/fruits.json");

            }
        });
    }
    private static String leendereco_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public class LeArquivoJSON extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return leendereco_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray =  jsonObject.getJSONArray("fruits");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Fruta(
                            productObject.getString("image"),
                            productObject.getString("name"),
                            productObject.getDouble("price")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            FrutaAdapter adapter = new FrutaAdapter(getApplicationContext(), R.layout.listafrutas, arrayList);
            listView.setAdapter(adapter);
        }
    }
}
