package com.franciscog;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaGetJSON extends CordovaPlugin {
    private final String TAG = "CordovaGetJSON";
    private CallbackContext getJSONcallback;
    
    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("get")) {
            getJSONcallback = callbackContext;

            String apiUrl = getPrefValue(args.getString(0));
            if (apiUrl != "") {
                try {
                    new getJSON().execute(apiUrl);

                } catch (Exception e) { }

            }
        }
        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT, "");
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        return true;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
        
    public String getPrefValue(String item) {
        String api = cordova.getActivity().getIntent().getStringExtra(item.toLowerCase(Locale.getDefault()));
        if (api == null) { api = ""; }
        return api;
    }

    //--------------------------------------------------------------------------
    // Async Class that opens connection and retrieves the JSON
    //--------------------------------------------------------------------------

    private class getJSON extends AsyncTask<String, Void, String> {
        private final String TAG = "CordovaGetJSON";

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            String responseString = "";

            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpStatus.SC_OK){
                    responseString = readInputStreamToString(urlConnection);
                    Log.v("getJSON", responseString);
                }else{
                    Log.v("getJSON", "Response code:"+ responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {

            PluginResult pluginResult = new  PluginResult(PluginResult.Status.OK, result);
            pluginResult.setKeepCallback(false);
            getJSONcallback.sendPluginResult(pluginResult);

        }

        /**
         * http://shayla.sawchenko.net/blog/android/2014/02/28/Reading-an-httpurlconnection-response/
         *
         * @param connection object; note: before calling this function,
         *   ensure that the connection is already be open, and any writes to
         *   the connection's output stream should have already been completed.
         * @return String containing the body of the connection response or
         *   null if the input stream could not be read correctly
         */
        private String readInputStreamToString(HttpURLConnection connection) {
            String result = null;
            StringBuffer sb = new StringBuffer();
            InputStream is = null;

            try {
                is = new BufferedInputStream(connection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                result = sb.toString();
            }
            catch (Exception e) {
                Log.i(TAG, "Error reading InputStream");
                result = null;
            }
            finally {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException e) {
                        Log.i(TAG, "Error closing InputStream");
                    }
                }
            }

            return result;
        }

    }


}
