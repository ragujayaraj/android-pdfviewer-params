package com.ragujayaraj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.ragujayaraj.pdfviewer.R;

import java.io.File;

/**
 * Created by vaseaharan on 31/3/16.
 */
public class SecondActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setDataAndType(Uri.fromFile(new File("mnt/sdcard/b.pdf?page=5&password=test")), "application/pdf");
        startActivity(intent);
    }
}