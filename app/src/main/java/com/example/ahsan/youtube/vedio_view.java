package com.example.ahsan.youtube;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class vedio_view extends AppCompatActivity {

    VideoView
            videoView;

    private static final int download = 0;
    String
            uriPath,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_view);

        videoView=(VideoView)findViewById(R.id.videoView) ;

        videoView.setVisibility(View.VISIBLE);



        Intent intent=getIntent();

          uriPath = intent.getStringExtra("url");
          title =intent.getStringExtra("title");
        Log.d("testurl",uriPath);
        Uri UrlPath=Uri.parse(uriPath);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(UrlPath);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                int position=0;
                if (position == 0) {
                    try{
                        videoView.requestFocus();
                        videoView.start();
                    }catch (Exception e){
                        System.out.printf("Error playing video %s\n", e);
                    }
                }else{
                    videoView.pause();
                }

            }
        });

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(uriPath));
                request.setTitle(title);
                request.setDescription("File is bing download....");

               // request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                String nameOffFile = URLUtil.guessFileName(uriPath,null, MimeTypeMap.getFileExtensionFromUrl(uriPath));
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,nameOffFile);
                DownloadManager manager=(DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);


            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuItem it1 = menu.add(Menu.NONE, download, Menu.NONE, "Download");
        it1.setIcon(R.drawable.down);
        it1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case download:

                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(uriPath));
                request.setTitle(title);
                request.setDescription("File is bing download....");

                // request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                String nameOffFile = URLUtil.guessFileName(uriPath,null, MimeTypeMap.getFileExtensionFromUrl(uriPath));
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,nameOffFile);
                DownloadManager manager=(DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);

                Toast.makeText(getBaseContext(), "Download this video", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }
}


