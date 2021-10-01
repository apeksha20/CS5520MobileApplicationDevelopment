## Lesson 2.1

### Github
[Lesson 2.1](https://github.com/apeksha20/CS5520MobileApplicationDevelopment/tree/main/Assignment2/Lesson2_1)

### My Learnings
* An Activity is a single screen in your app.
* An activity is started or activated with an intent.
* Each new activity you add to your project has its own layout and Java files, separate from those of the main activity.
* Intent is used to start another activity.
* Intent is used to send data from one activity to another while launching it.

### Screenshots


<img width="347" alt="Screen Shot 2021-09-30 at 11 12 16 PM" src="https://user-images.githubusercontent.com/17286896/135584212-ad8d6d3a-9758-4d5c-81c0-71f3d3c55637.png">&emsp; &emsp; &emsp; &emsp; <img width="347" alt="Screen Shot 2021-09-30 at 11 33 25 PM" src="https://user-images.githubusercontent.com/17286896/135584245-9fcc5a79-0b46-48bd-bd4a-5fb2033465dd.png">



<img width="347" alt="Screen Shot 2021-10-01 at 12 07 11 AM" src="https://user-images.githubusercontent.com/17286896/135585177-87d1253c-8d59-4ae7-96de-3a4b1914bb13.png">&emsp; &emsp; &emsp; &emsp; <img width="347" alt="Screen Shot 2021-10-01 at 12 07 32 AM" src="https://user-images.githubusercontent.com/17286896/135585226-098bfa92-e773-433b-a05d-7c89878b28d9.png">



<img width="347" alt="Screen Shot 2021-10-01 at 12 31 07 AM" src="https://user-images.githubusercontent.com/17286896/135585419-f685caa4-0323-4d38-8583-0bebc8bf14bf.png">&emsp; &emsp; &emsp; &emsp; <img width="347" alt="Screen Shot 2021-10-01 at 12 30 47 AM" src="https://user-images.githubusercontent.com/17286896/135585403-d094cef1-6abc-4a94-9931-4541ab51b82d.png">



## Lesson 2.2

### Github
[Lesson 2.2](https://github.com/apeksha20/CS5520MobileApplicationDevelopment/tree/main/Assignment2/Lesson2_2)

### My Learnings
* Learnt about activity lifecycle. The lifecycle methods are onCreate(), onStart(), onPause(), onRestart(), onResume(), onStop(), onDestroy().
* As the Device configuration changes such as rotation, the results in the Activity are destroyed and recreated as if it were new. So we need to Save Activity instance state in the onSaveInstanceState() method with key/value in bundle.

### Screenshots
<img width="347" alt="Screen Shot 2021-10-01 at 2 17 16 AM" src="https://user-images.githubusercontent.com/17286896/135599595-95f6bc06-b8b0-43c1-9a18-2e212a2af34e.png"> &emsp; &emsp; &emsp; &emsp; <img width="347" alt="Screen Shot 2021-10-01 at 2 17 33 AM" src="https://user-images.githubusercontent.com/17286896/135599638-c5e95652-66eb-4754-bbb2-f6557e1c17cb.png"> &emsp; &emsp; &emsp; &emsp; <img width="724" alt="Screen Shot 2021-10-01 at 2 19 28 AM" src="https://user-images.githubusercontent.com/17286896/135599663-f46c9461-330e-4707-8f47-c005d9b99ad1.png">

### After Implementing nSaveInstanceState() method


<img width="352" alt="Screen Shot 2021-10-01 at 2 32 03 AM" src="https://user-images.githubusercontent.com/17286896/135599977-f044f8af-d99b-4ee4-939c-07a4a62a9786.png"> &emsp; &emsp; &emsp; &emsp; <img width="723" alt="Screen Shot 2021-10-01 at 2 32 35 AM" src="https://user-images.githubusercontent.com/17286896/135600229-695aa54d-75c0-42af-972c-4c345983a7d8.png">

### Homework

### Github
[Lesson 2.2 Homework](https://github.com/apeksha20/CS5520MobileApplicationDevelopment/tree/main/Assignment2/Lesson2_2HomeWork)

<img width="345" alt="Screen Shot 2021-10-01 at 3 34 26 AM" src="https://user-images.githubusercontent.com/17286896/135609150-ce417ad4-eae8-4b57-bf98-876adbe6de4e.png"><img width="709" alt="Screen Shot 2021-10-01 at 3 35 02 AM" src="https://user-images.githubusercontent.com/17286896/135609205-0e5d64c7-cc49-4826-9593-5ecd7f2ec6de.png">

<img width="714" alt="Screen Shot 2021-10-01 at 3 55 22 AM" src="https://user-images.githubusercontent.com/17286896/135609270-95533929-3893-4799-8b57-bbe751be5e1c.png">

<img width="714" alt="Screen Shot 2021-10-01 at 3 55 48 AM" src="https://user-images.githubusercontent.com/17286896/135609294-95ccbcc5-a401-48c4-9f26-bcb1e744185c.png">&emsp; &emsp; &emsp; &emsp; <img width="348" alt="Screen Shot 2021-10-01 at 3 56 19 AM" src="https://user-images.githubusercontent.com/17286896/135609376-9c44637b-d30c-4a77-9fbd-f4e8e7fb7d12.png">&emsp; &emsp; &emsp; &emsp; <img width="348" alt="Screen Shot 2021-10-01 at 3 56 28 AM" src="https://user-images.githubusercontent.com/17286896/135609386-a63c4ef9-c635-477b-b22a-937e1ad4d0fa.png">



**Question 1**
If you run the homework app before implementing onSaveInstanceState(), what happens if you rotate the device? Choose one:

* The EditText no longer contains the text you entered, but the counter is preserved.
* The counter is reset to 0, and the EditText no longer contains the text you entered.
* **The counter is reset to 0, but the contents of the EditText is preserved.**
* The counter and the contents of the EditText are preserved.

**Question 2**
What Activity lifecycle methods are called when a device-configuration change (such as rotation) occurs? Choose one:

* Android immediately shuts down your Activity by calling onStop(). Your code must restart the Activity.
* Android shuts down your Activity by calling onPause(), onStop(), and onDestroy(). Your code must restart the Activity.
* **Android shuts down your Activity by calling onPause(), onStop(), and onDestroy(), and then starts it over again, calling onCreate(), onStart(), and onResume().**
* Android immediately calls onResume().

**Question 3**
When in the Activity lifecycle is onSaveInstanceState() called? Choose one:

* onSaveInstanceState() is called before the onStop() method.
* onSaveInstanceState() is called before the onResume() method.
* onSaveInstanceState() is called before the onCreate() method.
* **onSaveInstanceState() is called before the onDestroy() method.**

**Question 4**
Which Activity lifecycle methods are best to use for saving data before the Activity is finished or destroyed? Choose one:

* **onPause() or onStop()**
* onResume() or onCreate()
* onDestroy()
* onStart() or onRestart()
