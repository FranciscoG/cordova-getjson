//
//  CordovaGetJSON.h
//
//

#import <Cordova/CDV.h>
#import <Cordova/CDVViewController.h>

@interface CordovaGetJSON : CDVPlugin {
    NSString* _callbackId;
    NSData* _data;
}

- (void)get:(CDVInvokedUrlCommand*)command;
- (NSString *)getPref:(NSString *)pref;
- (void)getJSON:(NSString *)json_url;

@end
