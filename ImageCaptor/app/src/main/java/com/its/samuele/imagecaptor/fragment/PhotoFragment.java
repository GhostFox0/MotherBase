package com.its.samuele.imagecaptor.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.its.samuele.imagecaptor.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Samuele.Pretto on 30/09/2016.
 */

public class PhotoFragment extends Fragment {

    FloatingActionButton fab;
    ImageView showPhoto;
    Uri file;
    Bitmap bPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        showPhoto = (ImageView) view.findViewById(R.id.photoVisual);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},001);
                }
            }
        });

        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        if (requestCode == 0001){
            if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                takePhoto();
            }
            else {
                noPermission();
            }
        }
    }

    public void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());

        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100){
            if (resultCode == RESULT_OK){
                bPhoto = (Bitmap) data.getExtras().get("data");
                showPhoto.setImageBitmap(bPhoto);
            }
        }
    }
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Camera");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStampo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getAbsolutePath()+ File.separator+"IMG_"+timeStampo +".jpg");
    }

    public void noPermission(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Attenzione");
        alert.setMessage("Per usare l'app è necessario modificare i permessi");
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setPositiveButton("Permessi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(),null);
                intent.setData(uri);
                startActivityForResult(intent, 234, null);
            }
        });
        alert.create().show();
    }
}
