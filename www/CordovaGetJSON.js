var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    exec = require('cordova/exec');

function CordovaGetJSON() {}

/**
 * 
 * @param {String} api                The Preference name in your config.xml that contains the value you want
 * @param {Function} successCallback  The function to call when the heading data is available
 * @param {Function} errorCallback    The function to call when there is an error
 */
CordovaGetJSON.prototype.get = function (api, successCallback, errorCallback) {
    argscheck.checkArgs('sFf', 'CordovaGetJSON.get', arguments);
    exec(successCallback, errorCallback, "CordovaGetJSON", "get", [api]);
};

module.exports = new CordovaGetJSON();
