package com.saiproject.listview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomizedListView extends BaseAdapter {

    Context context;            // Interface to global information about an applicaiton
    LayoutInflater layoutInflater;          //Inflate customized list_view to content_main.xml's listView



    int[] animalImages = {R.drawable.bear, R.drawable.cow,R.drawable.tiger};
    String[] animalNames = {"Bear","Cow","Tiger"};
    int[] animalPowers = {200,20,150};
    int[] animalSpeeds = {100,20,250};


    public CustomizedListView(Context context) {


        this.context = context; // local variable context is the main class
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // Initialize layoutInflater

    }



// Methods to be implemented from the abstract class BaseAdapter


    @Override
    public int getCount() {
        return animalImages.length;
    }

    @Override
    public Object getItem(int position) {
        return animalImages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

// View to send to parent (content_main)

        view = layoutInflater.inflate(R.layout.customized_list_view,null);

        ImageView imgAnimal = (ImageView)view.findViewById(R.id.imgAnimal);
        final TextView txtAnimalName = (TextView)view.findViewById(R.id.txtAnimalName);
        TextView txtAnimalPower = (TextView)view.findViewById(R.id.txtAnimalPower);
        TextView txtAnimalSpeed = (TextView)view.findViewById(R.id.txtAnimalSpeed);






        String oldTxtAnimalName = txtAnimalName.getText().toString();
        String oldTxtAnimalPower = txtAnimalPower.getText().toString();
        String oldTxtAnimalSpeed = txtAnimalSpeed.getText().toString();


        txtAnimalName.setText(oldTxtAnimalName + animalNames[position]);
        txtAnimalPower.setText(oldTxtAnimalPower + animalPowers[position]);
        txtAnimalSpeed.setText(oldTxtAnimalSpeed + animalSpeeds[position]);


// To set up manually a bitmap
        /*
        final BitmapFactory.Options options = new BitmapFactory.Options(); // To convert images to lower resolution
        options.inSampleSize = 4;  // Higher sample size means lesser quality
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),animalImages[position],options);
        */


        //If clicked on the view shows a toast of animal name
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,txtAnimalName.getText(),Toast.LENGTH_LONG).show();
            }
        });



        imgAnimal.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),animalImages[position],50,50));

        return view;

    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }




}



