The plan

I want to be able to set multiple API urls in my config.xml like this:

    <preference name="CordovaGetJSON">
        <api name="uniqueApiName" url="https://api.url.com/whatever" />
        <api name="uniqueApiName1" url="https://api.url.com/whatever" />
        <api name="uniqueApiName2" url="https://api.url.com/whatever" />
    </preference>

Plugin makes asyc API calls on each of those urls immediately when an app is ready and has access to the network

on App device ready an an object is available with JSON results

    window.CordovaGetJSON = {
        uniqueApiName : JSON Result || undefined if failed,
        uniqueApiName1 : JSON Result || undefined if failed,
        uniqueApiName2 : JSON Result || undefined if failed
    };

Or, if possible, because we're dealing with Async calls it might be better to setup a custom event 

    document.addEventListener('CordovaGetJSON', function(resultObj){
      if (typeof resultObj.uniqueApiName !== "undefined") {
        // do something with the results
      }
      if (typeof resultObj.uniqueApiName1 !== "undefined") {
        // do something with the results
      }
      if (typeof resultObj.uniqueApiName2 !== "undefined") {
        // do something with the results
      }
    }, false);