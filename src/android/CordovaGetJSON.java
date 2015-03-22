package com.franciscog.CordovaGetJSON;

import java.util.Locale;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;;

// handling result:
// http://stackoverflow.com/questions/7751522/android-phonegap-notify-javascript-when-an-asynctask-is-finished/7849762#7849762

public class CordovaGetJSON extends CordovaPlugin {
    private final String TAG = "CordovaGetJSON";
    
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
            String apiUrl = getPrefValue();
            if (apiUrl != null) {
                // new getJSON().execute(apiUrl);
            }
        }
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
        
    public String getPrefValue() {

        // get the preference name="CordovaGetJSON"
        String prefName = "CordovaGetJSON";
        String api = cordova.getActivity().getIntent().getStringExtra(prefName.toLowerCase(Locale.getDefault()));
        return api;
    }

}

public class getJSON extends AsyncTask<String, Void, Void> {

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();
            
            if(responseCode == HttpStatus.SC_OK){
                String responseString = readInputStreamToString(urlConnection.getInputStream());
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
