## CordovaGetJSON

Sometimes you want to make api calls to urls and include senstive information like API keys.  This plugin allows you to make asynchronous calls to any URL that returns JSON from within the native platforms (iOS and Android only for now) instead of using XHR (AJAX) in your Javascript.

In your config.xml you set preferences with very unique names. Make sure they don't overlap any of the [possible config names available from Cordova](http://cordova.apache.org/docs/en/4.0.0/config_ref_index.md.html#The%20config.xml%20File)

```xml
    <preference name="myUniqueName" value="https://api.url.com/whatever" />
```

Then in your JS you start the API Call like this:
    
```javascript
    navigator.CordovaGetJSON.get("myUniqueName", 
      function(data){ // success callback
        // do something with the returned string/JSON: data
      },
      function(error){ //error callback
        // do something with the error
      });
```

Android minium level is 17

TODO:    
- add installation instruction (simple but people might need it)
- create tests
- get a 2nd opinion on code because it's my first time at both Android and Obj-C
