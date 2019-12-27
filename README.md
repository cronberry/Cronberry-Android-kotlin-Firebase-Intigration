# Cronberry-Android-kotlin-Firebase-Intigration
Contains firebase integration for android application and In App notification.

Usage
First:You have to create Firebase account then download the google-service.json and integrate it into your android project.

After adding Firebase messaging library in Build.gradle(Module:app) file
“implementation ‘com.google.firebase:firebase-messaging:17.3.2'”

and in Build.gradle(project:firebaseAppDemo) file, we have to put google plugin
“classpath ‘com.google.gms:google-services:4.2.0’”, Boom firebase is added.

Now in landing screen this code will help you to get FCM_Token
String firebaseToken = FirebaseInstanceId.getInstance().getToken();

you can save it to your SharedPreference.
-> after that call Register audience api using Retrofit,Volley or any other networking library.
-> when response from the api gets successful , then you are now registered with cronberry.
->For inApp notification   	in your xml file create a webView and load the URL provide by the Cronberry

"https://inapp.cronberry.com/mobile-view?audienceId=" + audienceId + "&" + "api-key=" + apiKey;

in this url you have to put your audienceID (UserID) and ApiKey is Unique Generated API Key 
then inapp notification will be loaded.
