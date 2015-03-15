var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    exec = require('cordova/exec');

function CordovaGetJSON() {}

/**
 * 
 *
 * @param {Function} successCallback The function to call when the heading data is available
 * @param {Function} errorCallback The function to call when there is an error getting the heading data. (OPTIONAL)
 */
CordovaGetJSON.prototype.getInfo = function (successCallback, errorCallback) {
    argscheck.checkArgs('fF', 'CordovaGetJSON.getInfo');
    exec(successCallback, errorCallback, "CordovaGetJSON", "startAPIcalls", []);
};

module.exports = new CordovaGetJSON();
