/**
 * Copyright 2014 Joan Zapata
 *
 * This file is part of Android-pdfview.
 *
 * Android-pdfview is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Android-pdfview is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Android-pdfview.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ragujayaraj;

import android.content.Intent;
import android.util.Log;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.ragujayaraj.pdfviewer.R;

import static java.lang.String.format;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.actionbar)
public class PDFViewActivity extends SherlockActivity implements OnPageChangeListener {

    public static final String SAMPLE_FILE = "";

    public static final String ABOUT_FILE = "about.pdf";

    private String fileString = "";

    @ViewById
    PDFView pdfView;

    @NonConfigurationInstance
    String pdfName = SAMPLE_FILE;

    @NonConfigurationInstance
    Integer pageNumber = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileString = getIntent().getDataString();
        if(fileString == null){
            Toast.makeText(PDFViewActivity.this, "File path not received", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            try {
                fileString = new URL(fileString).getPath();
            }catch (MalformedURLException e){
                Toast.makeText(PDFViewActivity.this, "MalformedURLException" + fileString, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                finish();
            }
            Toast.makeText(PDFViewActivity.this, "Opening " + fileString, Toast.LENGTH_SHORT).show();
        }
    }
    @AfterViews
    void afterViews() {
        displayFile(fileString, false);
    }

    @OptionsItem
    public void about() {
        if (!displaying(ABOUT_FILE))
            displayAsset(ABOUT_FILE, true);
    }

    private void displayAsset(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = assetFileName);

        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }

    private void displayFile(String fileName, boolean jumpToFirstPage) {
        if(fileName == null){
            Toast.makeText(PDFViewActivity.this, "File path null", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        File file = new File(fileName);
        if(file == null){
            Toast.makeText(PDFViewActivity.this, "File not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if(file.exists() == false){
            Toast.makeText(PDFViewActivity.this, "File does not exist", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = fileName);

        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }

    @Override
    public void onBackPressed() {
        if (ABOUT_FILE.equals(pdfName)) {
            displayFile(fileString, false);
        } else {
            super.onBackPressed();
        }
    }

    private boolean displaying(String fileName) {
        return fileName.equals(pdfName);
    }
}
