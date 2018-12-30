# UdacityProjectNewsApp

Here is an application for the Udacity Android Basics News project. This application shows a list of latest news related to "technology" using theguardian's api and allows the user to be directed to their website to read the article when the list is clicked on.

## PROJECT SPECIFICATION

### User Interface - Layout
- App contains a main screen which displays multiple news stories
- Each list item on the main screen displays relevant text and information about the story.
- Use of best practices.

### User Interface - Functionality
- Stories shown on the main screen update properly whenever new news data is fetched from the API.
- Clicking on a story opens up user's browser to the corresponding story.
- App queries the theguardian's api to fetch news stories related to the topic chosen by the user, using either the ‘test’ api key or the student’s key.
- Networking operations are done using a Async Loader rather than an AsyncTask.

## Screenshot

![news app](https://cloud.githubusercontent.com/assets/26686429/25818730/85a2c338-3423-11e7-8759-a6e018a056d4.png)
