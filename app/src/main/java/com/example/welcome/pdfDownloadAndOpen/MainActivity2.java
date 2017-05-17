package com.example.welcome.pdfDownloadAndOpen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.welcome.R;


/**
 * Created by ADMIN on 5/15/2017.
 */

public class MainActivity2 extends Activity{

    //    Button b1;
    // Progress dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        /* b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }


    public void downloadPDF(View v)
    {
//        new DownloadFile().execute("https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf", "five-point-someone-chetan-bhagat_ebook.pdf");
        new DownloadFile().execute("http://www.tutorialspoint.com/java/java_tutorial.pdf","java_tutorial.pdf");
    }

    public void viewPDF(View v)
    {
//        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + "five-point-someone-chetan-bhagat_ebook.pdf");  // -> filename = maven.pdf
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + "java_tutorial.pdf");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(MainActivity2.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
            String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hidepDialog();
            Toast.makeText(getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

