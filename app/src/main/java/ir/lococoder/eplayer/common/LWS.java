package ir.lococoder.eplayer.common;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class LWS {

    public static final int PROTOCOL_EXCEPTION = -1;
    public static final int IO_EXCEPTION       = -2;
    public static final int UNKNOWN_EXCEPTION  = -3;


    public interface Listener {
        void onSuccess(String data);
        void onFail(int statusCode);
    }

    private String                   cacheFileName;

    private String                   url;
    private JSONObject inputArguments;
    private Listener                 listener;
    private boolean                  enableCache;
    private String                   cacheDir;
    private long                     cacheExpireTime;
    private int                      connectionTimeout;
    private int                      socketTimeout;


    public LWS url(String value) {
        url = value;
        return this;
    }


    public LWS inputArguments(JSONObject value) {
        inputArguments = value;
        return this;
    }


    public LWS listener(Listener value) {
        listener = value;
        return this;
    }


    /**
     * @value path without last directory seprator <br/>
     *        ex: <b>/sdcard/your_project/cacheDir
     */
    public LWS cacheDir(String value) {
        cacheDir = value;
        return this;
    }


    public LWS enableCache(boolean value) {
        enableCache = value;
        return this;
    }


    public LWS cacheExpireTime(long value) {
        cacheExpireTime = value;
        return this;
    }


    public LWS connectionTimeout(int value) {
        connectionTimeout = value;
        return this;
    }


    public LWS socketTimeout(int value) {
        socketTimeout = value;
        return this;
    }


    public void read() {
        String data = null;
        if (enableCache) {
//            cacheFileName = createCacheFileName();
            data = readFromCache();
        }

        if (data == null) {
            readFromNet();
        } else {
            if (listener != null) {
                listener.onSuccess(data);
                return;
            }
        }
    }


    public void readFromNet() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpParams params = new BasicHttpParams();
                    HttpConnectionParams.setConnectionTimeout(params, connectionTimeout * 1000);
                    HttpConnectionParams.setSoTimeout(params, socketTimeout * 1000);
                    HttpClient client = new DefaultHttpClient(params);
                    HttpPost request = new HttpPost(url);
//                    HttpGet request = new HttpGet(url);
                    StringEntity se = new StringEntity(inputArguments.toString(),"UTF-8");
                    request.setEntity(se);

                    request.setHeader("Accept", "application/json");
                    request.setHeader("Content-viewType", "application/json");
                    HttpResponse httpResponse = client.execute(request);
                    String data = streamToString(httpResponse.getEntity().getContent());

                    if (enableCache) {
                        saveToCache(System.currentTimeMillis(), data);
                    }

                    if (listener != null) {
                        listener.onSuccess(data);
                        return;
                    }
                }
                catch (ClientProtocolException e) {
                    if (listener != null) {
                        listener.onFail(PROTOCOL_EXCEPTION);
                    }
                }
                catch (IOException e) {
                    if (listener != null) {
                        listener.onFail(IO_EXCEPTION);
                    }
                }
                catch (Exception e) {
                    if (listener != null) {
                        listener.onFail(UNKNOWN_EXCEPTION);
                    }
                }
            }
        });

        thread.start();
    }

//
//    private String createCacheFileName() {
//        String output = url;
//        for (JSONObject input: inputArguments) {
//            output += ";" + input.getName() + ":" + input.getValue();
//        }

//        String sha1 = sha1(output);
//        return sha1 + ".dat";
//    }

    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();


    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    private String sha1(String toHash) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = toHash.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();

            hash = bytesToHex(bytes);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hash;
    }


    private String streamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuffer = new StringBuilder();

        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append((line));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }


    private void saveToCache(long when, String data) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(cacheDir + "/" + cacheFileName));
            outputStream.writeLong(when);
            outputStream.writeInt(data.length());
            outputStream.write(data.getBytes());

        }
        catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String readFromCache() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(cacheDir + "/" + cacheFileName));

            long when = inputStream.readLong();
            long now = System.currentTimeMillis();
            if (now - when > cacheExpireTime * 1000) {
                new File(cacheDir + "/cache.dat").delete();
                return null;
            }

            int bytesLength = inputStream.readInt();
            byte[] buffer = new byte[bytesLength];
            inputStream.read(buffer, 0, bytesLength);
            String output = new String(buffer);
            return output;
        }
        catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}


// MyWeightWebService.saveWeight(weight);