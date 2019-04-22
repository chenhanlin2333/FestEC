package com.chen.latte.net;

import android.content.Context;

import com.chen.latte.net.callback.IError;
import com.chen.latte.net.callback.IFailure;
import com.chen.latte.net.callback.IRequest;
import com.chen.latte.net.callback.ISuccess;
import com.chen.latte.net.callback.LatteCallBack;
import com.chen.latte.net.download.Downloadhander;
import com.chen.latte.ui.LatteLaoder;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class RestCilent {

    private final String URL;
    private final Map<String, Object> PARAMS = RestCreater.getParams();
    private final ISuccess ISUCCESS;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final IFailure IFAILURE;
    private final RequestBody REQUESTBODY;
    private final Context MCONTENXT;
    private final File FILE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestCilent(String URLc,
                      ISuccess ISUCCESS,
                      Map<String, Object> map,
                      IError IERROR,
                      IRequest IREQUEST,
                      IFailure IFAILURE,
                      RequestBody REQUESTBODY,
                      File file,
                      Context Context,
                      String download_dir,
                      String extension,
                      String name) {
        this.URL = URLc;
        this.PARAMS.putAll(map);
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
        this.REQUESTBODY = REQUESTBODY;
        this.FILE = file;
        this.MCONTENXT = Context;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestCilentBuilder builder() {
        return new RestCilentBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreater.getRrest_Service();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequeststrat();
            if (MCONTENXT != null) {
                LatteLaoder.showLoading(MCONTENXT);
            }
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, REQUESTBODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUESTBODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);

                call = service.upload(URL, body);
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getCallback());
        }
    }

    public LatteCallBack getCallback() {
        return new LatteCallBack(ISUCCESS, IERROR, IREQUEST, IFAILURE);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) throw new RuntimeException("pararm must be null");
            request(HttpMethod.POST_RAW);
        }

    }

    public void put() {
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) throw new RuntimeException("pararm must be null");
            request(HttpMethod.PUT_RAW);
        }
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }


    public final void downlowad() {
        new Downloadhander(URL,PARAMS,ISUCCESS,IERROR,IREQUEST,IFAILURE,DOWNLOAD_DIR,EXTENSION,NAME)
        .handleDownload();
    }
}
