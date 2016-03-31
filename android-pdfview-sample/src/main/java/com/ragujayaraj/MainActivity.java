package com.ragujayaraj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ragujayaraj.pdfviewer.R;

/**
 * Created by vaseaharan on 31/3/16.
 */
public class MainActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Open your PDF file using any file explorer", Toast.LENGTH_LONG).show();
        finish();
    }
}
