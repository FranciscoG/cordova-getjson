var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    exec = require('cordova/exec');

function CordovaGetJSON() {}

/**
 * 
 * @param {String} api                The Preference name in your config.xml that contains the value you want
 * @param {Function} successCallback  The function to call when the heading data is available
 */
CordovaGetJSON.prototype.get = function (api, successCallback) {
    argscheck.checkArgs('sF', 'CordovaGetJSON.getInfo');
    exec(successCallback, errorCallback, "CordovaGetJSON", "startAPIcalls", [api]);
};

module.exports = new CordovaGetJSON();
