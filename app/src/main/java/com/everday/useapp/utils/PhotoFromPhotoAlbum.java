package com.everday.useapp.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/11/10
 * description: 相册路径
 */
public class PhotoFromPhotoAlbum {
    public static String getRealPathFromUri(Context mContext, Uri uri){
        int sdkVersion = Build.VERSION.SDK_INT;
        if(sdkVersion >= 19){
            return getRealPathFromUriAboveApi19(mContext,uri);
        }else{
            return getRealPathFromUriBelowAPI19(mContext,uri);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String getRealPathFromUriAboveApi19(Context mContext, Uri uri){
        String filePath = null;
        if(DocumentsContract.isDocumentUri(mContext,uri)){
            String documentId = DocumentsContract.getDocumentId(uri);
            if(isMediaDocument(uri)){
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + " =? ";
                String[] selectionArgs = {id};
                filePath = getDataColumn(mContext, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection,selectionArgs);
            }else if(isDownloadDocument(uri)){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(mContext,contentUri,null,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(mContext,uri,null,null);
        }else if("file".equals(uri.getScheme())){
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    private static String getRealPathFromUriBelowAPI19(Context mContext, Uri uri){
        return getDataColumn(mContext,uri,null,null);
    }

    /**
     * 获取数据库表中的_data列，及返回Uri对应的路径
     * @param mContext
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return 图片地址
     */
    private static String getDataColumn(Context mContext,Uri uri,String selection,String[] selectionArgs){
        String path = null;
        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(uri,projection,selection,selectionArgs,null);
            if(cursor!=null && cursor.moveToNext()){
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        }catch (Exception e){
            if(cursor!=null){cursor.close();}
        }
        return path;

    }
    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri){
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadDocument(Uri uri){
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
