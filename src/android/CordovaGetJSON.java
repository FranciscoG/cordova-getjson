package com.franciscog.CordovaGetJSON;

import java.util.Locale;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;;


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


    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
    
    public ArrayList<String> getUrls() {
        ArrayList urls = new ArrayList<String>();

        // get the preference name="CordovaGetJSON" and its children;
        String prefName = "CordovaGetJSON";
        String apis = cordova.getActivity().getIntent().getStringExtra(prefName.toLowerCase(Locale.getDefault()));

        for(int i=0;i<apis.length();i++){
            
            if (keyvalue != null) {
                urls.add(keyvalue);
            }
        }


        return urls;
    }

}