package parin_patel.example.com.thetechsamachartts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by parin_patel on 10/4/2017.
 */

public class MainActivity2 extends AppCompatActivity {
TextView title,author;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarScroll);
     //   setSupportActionBar(toolbar);
        title = (TextView) findViewById(R.id.txttitle2nd);
        author = (TextView) findViewById(R.id.author2nd);
        image = (ImageView) findViewById(R.id.image2nd);
        Intent intent = getIntent();
        String s = intent.getStringExtra("title");
        int s3 = intent.getIntExtra("image", 0);
        String s1 = intent.getStringExtra("author");
        title.setText(s);
        author.setText(s1);
        image.setImageAlpha(s3);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout_intent, menu);
        return true;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.shareintent) {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT,title.getText().toString());
//            i.putExtra(android.content.Intent.EXTRA_TEXT,author.getText().toString());
            //   i.putExtra("title",txtTitle);
            startActivity(Intent.createChooser(i,"Share via"));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
