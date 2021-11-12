### Github
[TODO App Code](https://github.com/apeksha20/CS5520MobileApplicationDevelopment/tree/main/Assignment5/TodoApp)

### Link to App on the Playstore
In progress(Need to create an apk as I am not able to publish due to not able to verify identity as I am an international students)

### What I did..
* I improved my TODO app so that notifications can be sent on the provided remind me date by the user while creating/editing a TODO task.
* First of all I modified the UI of the new task to incorporate the remind me checkbox and remind me date. 
* When a user adds a new task by clicking on the + icon on the home screen, then a remind me checkbox is visible.

<img width="351" alt="Screen Shot 2021-11-12 at 8 56 58 AM" src="https://user-images.githubusercontent.com/17286896/141505062-dc141430-41f2-4c41-a022-b8c70f4e01f1.png">

* On clicking on the remind me checkbox, the remind me due date text field is enabled so that user can add the date on which he needs to be reminded of the task. He can add date and time in the format mm/dd/yyyy HH:mm.

  <img width="351" alt="Screen Shot 2021-11-12 at 8 59 13 AM" src="https://user-images.githubusercontent.com/17286896/141505457-d023f1a3-18e8-4bc9-a08a-f45cc890d617.png">
* While adding/editing a task a notification(one time request) is then created and put in the work manager queue.
* Once the time is reached, the dowork() method of the worker we created is called which shows the notification on your phone screen.
* Used Pending intent to display the Task list of the TODO app once user clicks on the notification.



### My learnings
* Learnt about Work Manager.
* Learnt to create notifications with the help of Work manager.
* Learnt about Pending Intents and hoe it allows an action to be taken, example notification even when the app is closed.


### Screenshots
<img width="351" alt="Screen Shot 2021-11-12 at 8 42 46 AM" src="https://user-images.githubusercontent.com/17286896/141509420-8af1726e-046d-42f3-90af-903a76cd7d30.png">&nbsp;&nbsp;&nbsp;<img width="351" alt="Screen Shot 2021-11-12 at 8 43 06 AM" src="https://user-images.githubusercontent.com/17286896/141509510-513e502b-76ea-4c64-8a0b-33b1bd6d704c.png">
### Remind me time is set to 10:14. Current time 10:11 and currently no notification on the phone

<img width="351" alt="Screen Shot 2021-11-12 at 8 43 47 AM" src="https://user-images.githubusercontent.com/17286896/141509973-03bc143d-9a1c-48f8-8067-64d4f770c23c.png">&nbsp;&nbsp;&nbsp;<img width="351" alt="Screen Shot 2021-11-12 at 8 44 35 AM" src="https://user-images.githubusercontent.com/17286896/141510116-b3beba18-0e41-4e99-a54a-bb1a15926ca0.png">

### Current time 10:12 and no notification

<img width="351" alt="Screen Shot 2021-11-12 at 8 44 48 AM" src="https://user-images.githubusercontent.com/17286896/141510224-d1dd624a-3c7e-4b4c-b062-50013813f832.png">

### Notification received at 10:14

<img width="351" alt="Screen Shot 2021-11-12 at 8 46 44 AM" src="https://user-images.githubusercontent.com/17286896/141510366-a7ceb87b-cb58-423c-94c6-326a83a83896.png">

### On click of notification the task list opens up
<img width="351" alt="Screen Shot 2021-11-12 at 8 47 12 AM" src="https://user-images.githubusercontent.com/17286896/141510414-df3787ed-0b74-4eb2-9366-aefdd1f4f892.png">


