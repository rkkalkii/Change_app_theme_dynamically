
package com.example.welcome.change_app_theme;
/*
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MyUtils {
    public static int viewpagerPosition = 1;

    public static String getDeviceId(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = telephonyManager.getDeviceId();
        return deviceId;
    }

    public static String getDeviceEmail(Context context) {
        String deviceEmail = null;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                deviceEmail = account.name;

            }
        }
        return deviceEmail;
    }

    public static String getLocalPath() {
        String path = null;
        try {
            File sdCard = MyApplication.applicationContext
                    .getExternalFilesDir(null);
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "local");
            if (!dir.exists())
                dir.mkdirs();
            path = dir.getAbsolutePath() + "/";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getBookDirPath(long bookId) {
        String path = null;
        try {
            File sdCard = MyApplication.applicationContext
                    .getExternalFilesDir(null);
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "local");
            File bookDir;

            // if encode true directory names will be encoded
            if (Constants.Config.ENCODE) {
                bookDir = new File(dir, AndroidUtilities.md5(bookId + ""));
            } else
                bookDir = new File(dir, bookId + "");

            if (!bookDir.exists())
                bookDir.mkdirs();
            path = bookDir.getAbsolutePath() + "/";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getTagDirPath(long bookId, String trackableName) {
        String path = null;
        try {
            File sdCard = MyApplication.applicationContext
                    .getExternalFilesDir(null);
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "local");
            File bookDir;
            File campaignDir;
            // if encode true directory names will be encoded
            if (Constants.Config.ENCODE) {
                bookDir = new File(dir, AndroidUtilities.md5(bookId + ""));
                campaignDir = new File(bookDir, AndroidUtilities.md5(trackableName));
            } else {
                bookDir = new File(dir, bookId + "");
                campaignDir = new File(bookDir, trackableName);
            }
            if (!campaignDir.exists())
                // mkdirs creates all necessary parent directories
                campaignDir.mkdirs();
            path = campaignDir.getAbsolutePath() + "/";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getBookDirFile(long bookId, String fileName) {
        String path = null;
        try {
            File sdCard = MyApplication.applicationContext
                    .getExternalFilesDir(null);
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "local");
            File bookDir;
            File filePath;
            // if encode true directory names will be encoded
            if (Constants.Config.ENCODE) {
                bookDir = new File(dir, AndroidUtilities.md5(bookId + ""));
                filePath = new File(bookDir, AndroidUtilities.md5(fileName));
            } else {
                bookDir = new File(dir, bookId + "");
                filePath = new File(bookDir, fileName);
            }
            if (!bookDir.exists())
                bookDir.mkdirs();
            path = filePath.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static String getTagDirFile(long bookId, String trackableName, String fileName) {
        String path = null;
        try {
            File sdCard = MyApplication.applicationContext
                    .getExternalFilesDir(null);
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "local");
            File bookDir;
            File campaignDir;
            File filePath;

            // if encode true directory names will be encoded
            if (Constants.Config.ENCODE) {
                bookDir = new File(dir, AndroidUtilities.md5(bookId + ""));
                campaignDir = new File(bookDir, AndroidUtilities.md5(trackableName));
                filePath = new File(campaignDir, AndroidUtilities.md5(fileName));
            } else {
                bookDir = new File(dir, bookId + "");
                campaignDir = new File(bookDir, trackableName);
                filePath = new File(campaignDir, fileName);
            }
            if (!campaignDir.exists())
                // mkdirs creates all necessary parent directories
                campaignDir.mkdirs();
            path = filePath.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static boolean deleteDirectory(File path) {
        if (!path.exists())
            return true;
        File[] files = path.listFiles();
        if (files == null)
            return path.delete();

        for (File file : files) {
            if (file.isDirectory())
                deleteDirectory(file);
            else file.delete();
        }
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].isDirectory())
//                deleteDirectory(files[i]);
//            else files[i].delete();
//        }
        return path.delete();
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public static boolean deleteFreeBookPage(MyTag myTag) {
        File path = new File(MyUtils.getTagDirPath(myTag.getBookId(), myTag.getTrackableName()));
        String thumbnailFileName = myTag.getTrackableName() + "_small.png";

        if (!path.exists())
            return true;
        File[] files = path.listFiles();
        if (files == null)
            return path.delete();

        for (File file : files) {
            if (file.isDirectory()) {
                File contentFiles[] = file.listFiles();
                for (int i = 0; i < contentFiles.length; i++) {
                    if (!contentFiles[i].getName().equals(thumbnailFileName))
                        contentFiles[i].delete();
                }
            } else file.delete();
        }
        return true;
    }
//    public static boolean deleteDirectory(File path) {
//        if (path.exists()) {
//            File[] files = path.listFiles();
//            if (files == null) {
//                return true;
//            }
//            for (int i = 0; i < files.length; i++) {
//                if (files[i].isDirectory()) {
//                    deleteDirectory(files[i]);
//                } else {
//                    files[i].delete();
//                }
//            }
//        }
//        return (path.delete());
//    }

    public static void loadImage(ImageView imageView, String imageUrl) {
        DisplayImageOptions options = null;
        if (options == null)
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_stub)
                    .showImageForEmptyUri(R.drawable.ic_stub)
                    .showImageOnFail(R.drawable.ic_stub)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .build();
        ImageLoader.getInstance().displayImage(imageUrl,
                imageView, options, new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                                                  Bitmap loadedImage) {
                        if (loadedImage != null) {
                            ImageView imageView = (ImageView) view;
                            FadeInBitmapDisplayer.animate(imageView, 1000);
                        }
                    }

                }
        );
    }

    public static void storeBook(Context context, Book book, int part) {
        String PREFS_NAME = "pref_book";
        String KEY_BOOK = "book";
        String KEY_PART = "part";

        // Serialize the object into a string
        String serializedData = book.serialize();

        // Save the serialized data into a shared preference
        SharedPreferences preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesReader.edit();
        editor.putString(KEY_BOOK, serializedData);
        editor.putInt(KEY_PART, part);
        editor.apply();
    }

    public static Object[] retrieveBook(Context context) {
        String PREFS_NAME = "pref_book";
        String KEY_BOOK = "book";
        String KEY_PART = "part";

        SharedPreferences preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String serializedDataFromPreference = preferencesReader.getString(KEY_BOOK, null);

        // Create a new object from the serialized data with the same state
        //return Book.create(serializedDataFromPreference);
        Object[] bookAndPart = new Object[2];
        bookAndPart[0] = Book.create(serializedDataFromPreference);
        bookAndPart[1] = preferencesReader.getInt(KEY_PART, 0);
        return bookAndPart;
    }

    public static void goToWifiSetting(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final ComponentName cn = new ComponentName("com.android.settings",
                "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

   */
/* public static boolean canLaunchCamera(Context context) {
        // retrieve catalog for sharedPref
        Book book = MyUtils.retrieveBook(context);
        if (book == null)
            return false;
        File fileDat = new File(MyUtils.getBookDirFile(book.getId(), book.getDatName()));
        File fileXml = new File(MyUtils.getBookDirFile(book.getId(), book.getXmlName()));
        return (fileDat.exists() && fileXml.exists());
    }*//*


    public static boolean canLaunchCamera(Context context) {
        Object[] bookAndPart = MyUtils.retrieveBook(context);
        Book book = (Book) bookAndPart[0];

        if (book == null)
            return false;

        boolean isDownloaded = true;
        if (book.getParts() == null || book.getParts().size() == 0) {
            File fileDat = new File(MyUtils.getBookDirFile(book.getId(), book.getDatName()));
            File fileXml = new File(MyUtils.getBookDirFile(book.getId(), book.getXmlName()));

            if (!fileDat.exists() && !fileXml.exists())
                isDownloaded = false;
        } else {
            for (Book.Part part : book.getParts()) {
                File fileDat = new File(MyUtils.getBookDirFile(book.getId(), part.getDatName()));
                File fileXml = new File(MyUtils.getBookDirFile(book.getId(), part.getXmlName()));
                if (!fileDat.exists() && !fileXml.exists()) {
                    isDownloaded = false;
                    break;
                }
            }
        }
        return isDownloaded;
    }

    */
/*public static void launchCamera(Context context) {
        if (!MyUtils.canLaunchCamera(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton(R.string.mybooks, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton(R.string.search, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            return;
        }

        Catalog catalog = MyUtils.retrieveBook(context);
        Intent intent = new Intent(context, ImageTargets.class);
        intent.putExtra(ImageTargets.BOOK, catalog);
        intent.putExtra(ImageTargets.PATH, MyUtils.getBookDirPath(catalog.getBookId()) + catalog.getBookId() + ".xml");
        context.startActivity(intent);
    }*//*


    public static JSONObject readFileFromPath(File file) {
        JSONObject jsonObject = null;
        StringBuilder json = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                json.append(line);
            }

            if (Constants.Config.ENCODE) {
                jsonObject = new JSONObject(AndroidUtilities.base64toString(json.toString()));
            } else {
                jsonObject = new JSONObject(json.toString());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

    public static JSONArray readJsonArrayFromPath(File file) {
        JSONArray jsonArray = null;
        StringBuilder json = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                json.append(line);
            }

            if (Constants.Config.ENCODE) {
                jsonArray = new JSONArray(AndroidUtilities.base64toString(json.toString()));
            } else {
                jsonArray = new JSONArray(json.toString());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }

    public static boolean isBookDataSetExist(Book book){
        boolean isDownloaded = false;

        if (book == null)
            return false;

    if (book.getParts() == null || book.getParts().size() == 0) {
        File fileDat = new File(MyUtils.getBookDirFile(book.getId(), book.getDatName()));
        File fileXml = new File(MyUtils.getBookDirFile(book.getId(), book.getXmlName()));

        if (fileDat.exists() && fileXml.exists())
            isDownloaded = true;
    } else {
        for (Book.Part part : book.getParts()) {
            File fileDat = new File(MyUtils.getBookDirFile(book.getId(), part.getDatName()));
            File fileXml = new File(MyUtils.getBookDirFile(book.getId(), part.getXmlName()));
            if (fileDat.exists() && fileXml.exists()) {
                isDownloaded = true;
                break;
            }
        }
    }
    return isDownloaded;
    }


// This functionality is for to change App content as per local environment  ie. testing or publishing.
    public static ArrayList synchronizeAppWithLocalEnvironment(){
        String path = null;
        ArrayList environmentData = new ArrayList();

        try {
            File sdCard  = Environment.getExternalStorageDirectory();
            if (sdCard == null) {
                return null;
            }
            File dir = new File(sdCard, "Environment");

            String  fileName = "environment.txt";

        //    File filePath = new File(dir, fileName);
            if(!dir.exists())
                return  null;
            path = dir.getAbsolutePath();

            String completePath = path+ "/" + fileName;
            File currentPath = new File(completePath);
            if(!currentPath.exists())
                return  null;
            JSONObject jsonObject = readFileFromPath(currentPath);
            environmentData = JSONParser.parseAppEnvironmentData(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return environmentData;
    }


}
*/
