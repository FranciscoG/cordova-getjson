//
//  CordovaGetJSON.m
//

#import "CordovaGetJSON.h"

@interface CordovaGetJSON (PrivateMethods)
- (void)sendPluginResult;
@end

@implementation CordovaGetJSON

- (void)sendPluginResult
{
    NSString* myString = [[NSString alloc] initWithData:_data encoding:NSUTF8StringEncoding];
    CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:myString];
    [result setKeepCallbackAsBool:NO];
    [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
}

- (void)get:(CDVInvokedUrlCommand*)command
{
    _callbackId = command.callbackId;
    CDVPluginResult* result = nil;
    NSArray* options = command.arguments;
    
    if (!options) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"no setting keys"];
        [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
        return;
    }
    
    @try {
        NSString *myPref = [self getPref:options[0]];
        if ([myPref length] > 0) {
            [self getJSON:myPref];
            result  =  [CDVPluginResult resultWithStatus:CDVCommandStatus_NO_RESULT messageAsString:@""];
            [result setKeepCallbackAsBool:YES];
        } else {
            result  =  [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"preference not found"];
            [result setKeepCallbackAsBool:NO];
            [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
        }
    } @catch (NSException* e) {
        NSLog(@"Uncaught exception: %@, %@", [e description],[e callStackSymbols]);
        @throw e;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_NO_RESULT messageAsString:[e reason]];
        [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
    }
}

- (NSString *)getPref:(NSString *)pref
{
    NSDictionary *sets = self.commandDelegate.settings;
    if (sets [[pref lowercaseString]] != nil) {
        return sets [[pref lowercaseString]];
    } else {
        return @"";
    }
}

- (void)getJSON:(NSString *)json_url
{
    NSURL *url = [NSURL URLWithString:json_url];
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:url];
    NSOperationQueue *queue = [[NSOperationQueue alloc] init];
    [NSURLConnection sendAsynchronousRequest:urlRequest queue:queue completionHandler:^(NSURLResponse *response, NSData *data, NSError *error)
     {
         
         if (error)
         {
             NSLog(@"Error,%@", [error localizedDescription]);
         }
         else
         {
             _data = data;
             [self sendPluginResult];
         }
     }];
}

@end
