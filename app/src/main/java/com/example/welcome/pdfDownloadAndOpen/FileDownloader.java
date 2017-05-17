package com.example.welcome.pdfDownloadAndOpen;

/**
 * Created by ADMIN on 5/15/2017.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {
    private static final int  MEGABYTE = 1024 * 1024;

    //trial.................
    public static void downloadFile(String fileUrl, File directory){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer)) >0){
            fileOutputStream.write(buffer, 0, bufferLength);
        }
        fileOutputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}