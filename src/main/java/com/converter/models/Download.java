package com.converter.models;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Download {
    Download(String date,String file){

        String url="https://www.cbr.ru/scripts/XML_daily.asp?date_req="+date;
        try{
            downloadUsingStream(url,file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void downloadUsingStream(String urlStr,String file) throws IOException{

        URL url=new URL(urlStr);

        BufferedInputStream bis=new BufferedInputStream(url.openStream());
        FileOutputStream fos=new FileOutputStream(file);

        byte[] buffer=new byte[1024];
        int count=0;
        while ((count=bis.read(buffer,0,1024))!=-1){
            fos.write(buffer,0,count);
        }
        fos.close();
        bis.close();
    }
}
