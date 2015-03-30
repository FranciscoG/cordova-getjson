//
//  CordovaGetJSON.m
//  

#import "CordovaGetJSON.h"

CDVPluginResult* result = nil;

@implementation CordovaGetJSON

- (void)get:(CDVInvokedUrlCommand*)command
{
    
    NSArray* options = command.arguments;

    if (!options) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"no setting keys"];
        [self.commandDelegate sendPluginResult:result callbackId:[command callbackId]];
        return;
    }

    @try {
        NSString *myPref = [self getPref:options[0]];
        [self getJSON:myPref myCommand:command];
        result  =  [CDVPluginResult resultWithStatus:CDVCommandStatus_NO_RESULT messageAsString:@""];
        [result setKeepCallbackAsBool:YES];
    } @catch (NSException * e) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_NO_RESULT messageAsString:[e reason]];
        [result setKeepCallbackAsBool:NO];
        [self.commandDelegate sendPluginResult:result callbackId:[command callbackId]];
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

// http://stackoverflow.com/questions/16607883/asynchronous-request-example
- (void)getJSON:(NSString *)json_url myCommand:(CDVInvokedUrlCommand*)command
{
    NSURL *url = [NSURL URLWithString:json_url];
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:url];
    NSOperationQueue *queue = [[NSOperationQueue alloc] init];
    [NSURLConnection sendAsynchronousRequest:urlRequest queue:queue completionHandler:^(NSURLResponse *response, NSData *data, NSError *error)
    {
        
        if (error)
        {
            //NSLog(@"Error,%@", [error localizedDescription]);
        }
        else
        {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: [data copy]];
            [result setKeepCallbackAsBool:NO];
            [self.commandDelegate sendPluginResult:result callbackId:[command callbackId]];
        }
    }];
}

@end
