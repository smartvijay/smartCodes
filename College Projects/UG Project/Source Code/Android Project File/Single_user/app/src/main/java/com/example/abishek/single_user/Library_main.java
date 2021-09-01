    package com.example.abishek.single_user;

    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;

    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;

    public class Library_main extends AppCompatActivity {

        private RecyclerView RecyclerView;
        private Library_Adapter LibraryAdapter;
        private ArrayList<Library_Item> libraryList;
        private com.android.volley.RequestQueue RequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_main_activity);

        setTitle("Library");

        if (getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        RecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        libraryList = new ArrayList<>();

        RequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

        private void parseJSON() {

            // Mysql DataBase
            // String url = "http://192.168.225.45/Official/mysql_library.php";

            // Postgres DataBase
             //    String url = "http://192.168.43.74/Official_pg/pg_library.php";
            final String url = "http://192.168.225.45/project/single_user_mysql/library.php";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("library");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject library = jsonArray.getJSONObject(i);

                                    String book_title = library.getString("book_title");
                                    int book_ac_no = library.getInt("book_ac_no");
                                    String book_resource_type = library.getString("book_resource_type");
                                    String book_issue_date = library.getString("book_issue_date");
                                    String book_due_date = library.getString("book_due_date");
                                    String book_return_date = library.getString("book_return_date");
                                    String book_status = library.getString("book_status");

                                    libraryList.add(new Library_Item( book_title,
                                            book_ac_no,
                                            book_resource_type,
                                            book_issue_date,
                                            book_due_date,
                                            book_return_date,
                                            book_status));
                                }

                                LibraryAdapter = new Library_Adapter(Library_main.this, libraryList);
                                RecyclerView.setAdapter(LibraryAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            RequestQueue.add(request);
        }
    }

