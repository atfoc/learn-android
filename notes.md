# 02 Advance android
## Timers
+	`Timer` - run on period of time after delay
	+	`fixedRate` it is used to run on strict period meaning if one execution is late others will speed up to catch up
	+ normal it is used when smoothness is required meaning if one execution is late it will not speed up to catchup
+	`CountDownTimer` - used to count down certain time with tick period
+	`Handler` - is a mechanism that allows you to delay action for certain amount of time
+	`TimeUnit` very useful for converting time from seconds to milliseconds and similar

# 03 Crime Intent
## Fragments
+	Fragments are used to outsource UI management to fragments classes from activity so 
you can more easily support tablet and phone design by not having to rewrite same ui elements
for both instead just map fragments differently. And also help on reuse of ui elements 
+	Fragments have there life cycle methods similarly to activates [Fragments life cycle](https://developer.android.com/images/fragment_lifecycle.png)
+	Fragments are made similarly as activates you need:
	+	Create a layout file
	+	Implement Life cycle methods
	+	Inflate layout file
+	You have two ways to add a fragment to your activity
	+	Add it in layout file less flexible it dose not support swapping fragments but simpler
	+	Add it form code more flexible but harder

### Fragments life cycle
+	`onCreate()` is used for setting up states of fragment(members and so on), returning saved state and similar
but it is not used for inflating layout file
+	`onCreateView()` is used to inflate layout file as well as setup references to ui widgets and set up handlers and similar


