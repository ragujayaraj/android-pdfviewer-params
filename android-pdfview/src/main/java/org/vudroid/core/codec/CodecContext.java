package org.vudroid.core.codec;

import android.content.ContentResolver;

public interface CodecContext
{
    CodecDocument openDocument(String fileName);

    CodecDocument openDocument(String fileName, String pwd);

    void setContentResolver(ContentResolver contentResolver);

    void recycle();
}
