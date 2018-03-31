# UdacityProjectNewsApp

Here is an application for the Udacity Android Basics News project. This application shows a list of latest news related to "technology" using theguardian's api and allows the user to be directed to their website to read the article when the list is clicked on.

## PROJECT SPECIFICATION

### User Interface - Layout
- App contains a main screen which displays multiple news stories
- Each list item on the main screen displays relevant text and information about the story.
- Use of best practices.

### User Interface - Functionality
- Stories shown on the main screen update properly whenever new news data is fetched from the API.
- Clicking on a story opens the story in the user’s browser.
- App queries the content.guardianapis.com api to fetch news stories related to the topic chosen by the student, using either the ‘test’ api key or the student’s key.
- Networking operations are done using a Loader rather than an AsyncTask.
- The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for the core functionality will not be permitted to complete this project.

## Screenshot(s)

![news app](https://cloud.githubusercontent.com/assets/26686429/25818730/85a2c338-3423-11e7-8759-a6e018a056d4.png)

## License

    Copyright 2017 Divit Bui

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
