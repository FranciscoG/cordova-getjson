//
//  CordovaGetJSON.m
//  

#import "CordovaGetJSON.h"

@interface CordovaGetJSON (PrivateMethods)
- (void)getPref;
- (void)getJSON;
@end

@implementation CordovaGetJSON

- (void)get:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* result = nil;

    NSArray* options = command.arguments;

    if (!options) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"no setting keys"];
        [self.commandDelegate sendPluginResult:result callbackId:[command callbackId]];
        return;
    }

    @try {
        NSString *myPref = [self getPref:options[0]];

        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: [myPref copy]];
    } @catch (NSException * e) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_NO_RESULT messageAsString:[e reason]];
    } @finally {
        [result setKeepCallbackAsBool:YES];
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
- (void)getJSON:(NSString *)json_url
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
            //NSLog(@"%@", [[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding]);
        }
    }];
}

@end
