
### Github
[TODO App Code](https://github.com/apeksha20/CS5520MobileApplicationDevelopment/tree/main/Assignment4/TodoApp)

### Link to App on the Playstore
In progress(Facing issues in verifying the identity)

### What I did..
* Refactored my  TODO app which is a simple app, through which we can add a task, its details, set the tag priority to high, medium, low and set a deadline date.  
* Previously my app didn't persist data, but now My app persist data and when I close and reopen the app, the saved tasks are seen the app. 
* The data gets locally saved with the help of Room database.
* Used Recycler view to display the list of tasks on the main screen and then created a view model and a repository and established a connection between them.
  Also created a DAO(data access object) and used Live data.

### My learnings
* Learnt about room database persistence Library.
* Learnt about how to persist data in an andriod app, so that data is not lost whenever we turn off the app.
* Learnt how to add onclick listners with the recycler view
* Learnt about how to use a recycler view to display list of TODO tasks.
* Also I refactored my code to use same activity for adding and editing tasks instead of creating separate copies. Code Reusability met and code duplication avoided.

### Home Screen of the TODO App
* The app's home page displays any task created. It mentions the task title and the due date. 
* There is a + icon to add a new task.

<img width="351" alt="Screen Shot 2021-11-10 at 5 33 54 PM" src="https://user-images.githubusercontent.com/17286896/141221397-c2f21003-f638-451d-a430-cdd35b11e23e.png">

### Add a New Task Screen

<img width="351" alt="Screen Shot 2021-11-10 at 5 38 19 PM" src="https://user-images.githubusercontent.com/17286896/141221476-2677acd0-64be-48ad-be48-6209b0c17bc0.png">

### Adding Task

<img width="351" alt="Screen Shot 2021-11-10 at 5 35 11 PM" src="https://user-images.githubusercontent.com/17286896/141221519-073f9e9f-4450-487d-ba77-d7e83f32b29f.png">

### After Adding a new task

<img width="351" alt="Screen Shot 2021-11-10 at 5 35 02 PM" src="https://user-images.githubusercontent.com/17286896/141221599-28062d89-fc67-4418-93c3-0d9afe0a94df.png">

### Checking on the chewckbox marks the task complete and background color changes to yellow 

<img width="351" alt="Screen Shot 2021-11-10 at 5 35 56 PM" src="https://user-images.githubusercontent.com/17286896/141221675-61457614-e8c3-4135-b416-bb9a80dc5a37.png">


