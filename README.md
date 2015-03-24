
# This plugin is in development and doesn't work yet.  DO NOT USE


## CordovaGetJSON

Sometimes you want to make api calls to urls and include senstive information like API keys.  This plugin allows you to make asynchronous calls to any URL that returns JSON from within the native platforms (iOS and Android only for now) instead of using XHR (AJAX) in your Javascript.

In your config.xml you set preferences with very unique names. Make sure they don't overlap any of the [possible config names available from Cordova](http://cordova.apache.org/docs/en/4.0.0/config_ref_index.md.html#The%20config.xml%20File)

```xml
    <preference name="Api_URL_1" value="https://api.url.com/whatever" />
    <preference name="Api_URL_2" value="https://api.url.com/something_else" />
    <preference name="Api_URL_3" value="https://api.url.com/blablabla" />
```

Then in your JS you start the API Call like this:
    
```javascript
    navigator.CordovaGetJSON.get("Api_URL_1", callBackFunction);
    navigator.CordovaGetJSON.get("Api_URL_2", callBackFunction);
    navigator.CordovaGetJSON.get("Api_URL_3", callBackFunction);
```

The callback function gets passed the resulting JSON or error
