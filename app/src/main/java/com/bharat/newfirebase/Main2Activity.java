package com.bharat.newfirebase;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.ramotion.foldingcell.FoldingCell;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   private DrawerLayout drawer;
    private NavigationView nv;
    private ActionBarDrawerToggle toggle;
    public String accountName,picUri;
    private Uri accountPhoto;
    private TextView name;
    private final Integer[] Quantity= new Integer[]{0,1,2,3,4,5,6,7,8,9,10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name= (TextView) findViewById(R.id.tv);
        accountName= getIntent().getStringExtra("string");
        picUri=getIntent().getStringExtra("photo");
        accountPhoto= Uri.parse(picUri);
        final FoldingCell fc= (FoldingCell) findViewById(R.id.foldingCell);
        Spinner quanity= (Spinner) findViewById(R.id.numOfBags);
        final TextView total= (TextView) findViewById(R.id.toatal);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,Quantity);
        quanity.setAdapter(adapter);
        int initial=0;
        int spinnerPosition = adapter.getPosition(initial);
        quanity.setSelection(spinnerPosition);
        quanity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                int value= (Integer.valueOf(item.toString()))*700;
                String result= String.valueOf(value);
                total.setText(result+"/-");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nv= (NavigationView) findViewById(R.id.navigation_draw);
        View hView =  nv.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tvh);
        nav_user.setText(accountName);
        CircularImageView imageView= (CircularImageView) hView.findViewById(R.id.circularImg);
        Glide.with(this).load(accountPhoto).into(imageView);
        toggle =new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:

                Toast.makeText(this, "My Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "Manage Orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "About Supplier", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
