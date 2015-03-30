//
//  AppPreferences.h
//  
//

#import <Cordova/CDV.h>
#import <Cordova/CDVViewController.h>

@interface CordovaGetJSON : CDVPlugin 

- (void)get:(CDVInvokedUrlCommand*)command;
- (NSString *)getPref:(NSString *)pref;
- (void)getJSON:(NSString *)json_url myCommand:(CDVInvokedUrlCommand*)command;
+ (CDVPluginResult*) result;

@end
