package parin_patel.example.com.thetechsamachartts;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String URL_DATA = "http://www.thetechsamachar.com/api/get_posts/?count=15/";
    private RecyclerView recyclerView;
  //  ImageButton imgbutton;
    ImageView imgview;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private onloadrecyclerview scrolllistener;
    private ProgressBar spinner;
    private List<PostItems> postItems;
    Switch nightmode;
    private Layout relative;
    SearchView serch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("The Tech Samachar");
        toolbar.setTitleTextColor(Color.parseColor("#090909"));

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        nightmode = (Switch) findViewById(R.id.nightmode);
        imgview = (ImageView) findViewById(R.id.imgView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postItems = new ArrayList<>();
        loadRecyclerViewData();
        serch = (SearchView) findViewById(R.id.search);



        {

            fab = (FloatingActionButton) findViewById(R.id.fab1);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                   /* String emailUrl = "mailto:admin@thetechsamachar.com ?&body=Write If Any Query...!!!";
                    Intent request = new Intent(Intent.ACTION_VIEW);
                    request.setData(Uri.parse(emailUrl));
                    startActivity(request);
*/
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("message/rfc822");
                    List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
                    if (queryIntentActivities.isEmpty()) {
                    } else {
                        boolean jd = false;
                        for (ResolveInfo resolveInfo : queryIntentActivities) {
                            boolean panir;
                            if (resolveInfo.activityInfo.packageName.toLowerCase().contains("com.google.android.gm") || resolveInfo.activityInfo.name.toLowerCase().contains("com.google.android.gm")) {
                                intent.setType("message/rfc822");
                                intent.putExtra("android.intent.extra.EMAIL", new String[]{"admin@thetechsamachar.com"});
                                intent.putExtra("android.intent.extra.SUBJECT", "Query at The Tech Samachar");
                                intent.setPackage(resolveInfo.activityInfo.packageName);
                                startActivity(Intent.createChooser(intent, "Send mail..."));
                                panir = true;
                            } else {
                                panir = jd;
                            }
                            jd = panir;
                        }
                    }

                }
            });


            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toolbar = (Toolbar) findViewById(R.id.toolbar1);
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }


    }
    private void loadRecyclerViewData() {

     /*   final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...!!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
*/

        spinner.setVisibility(View.VISIBLE);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
  /*                      progressDialog.dismiss();*/
                        spinner.setVisibility(View.GONE);

                        try {

                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("posts");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                JSONObject img = ob.getJSONObject("thumbnail_images");
                                JSONObject index = img.getJSONObject("medium_large");
                                JSONObject writer = ob.getJSONObject("author");

                                String title = Html.fromHtml((String) ob.get("title")).toString();
                             //   String content = Html.fromHtml((String) ob.get("content")).toString();
                                String author = Html.fromHtml((String) writer.get("name")).toString();
                              //  Date date = (Date) Html.fromHtml(String.valueOf((Date) ob.get ("date")));
//                                String content = Html.fromHtml((String) ob.get("excerpt")).toString();
//                                String excerpt = content.substring(0, 150).concat("...");
                                String url = String.valueOf(Html.fromHtml(index.getString("url")));


                                PostItems postItem = new PostItems(
                                        title,
                                        author,
                                  //      content,
                                    //    date,
                                        url


                                );

                                postItems.add(postItem);
                            }

                            adapter = new PostAdapter(postItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Snackbar sb = Snackbar.make(drawer,"No Internet Connection", 8000);
                        sb.show();
                        sb.setActionTextColor(Color.RED);
                        View sbView = sb.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.YELLOW);
                        sb.show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    public void onBackPressed()  {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setTitle("Are You Sure");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // finish used for destroyed activity
                    finish();

                }
            });

            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {



                }
            });

            alertbox.show();
        }




        return super.onKeyDown(keyCode, event);
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       /* noinspection SimplifiableIfStatement*/

        if (id == R.id.rateus) {

            return true;
        }
        if (id == R.id.share) {
          /*  Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
            startActivity(shareIntent);*/
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://drive.google.com/drive/u/1/folders/0B9e9WhouihsHSVdHV3R6YXBHT3c");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tech) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_security) {

        } else if (id == R.id.nav_attack) {

        } else if (id == R.id.nav_did) {

        } else if (id == R.id.nav_research) {

        } else if (id == R.id.notification) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }


}
