RoundLabelTextView
==============================================================
#example


        <com.github.namelesspeople.roundlabeltextview.RoundLabelTextView
              android:id="@+id/round_text"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              app:angle="2"
              app:contentMarginBottom="0dp"
              app:contentMarginStart="0dp"
              app:contentText="@string/content_name"
              app:contentTextColor="#ffffff"
              app:contentTextSize="15sp"
              app:labelLength="100dp"
              app:radius="0dp"
              app:topMarginBottom="0dp"
              app:topMarginStart="0dp"
              app:topText="@string/top_name"
              app:topTextColor="#ffffff" />

	
[![](https://www.jitpack.io/v/NamelessPeople/RoundLabelTextView.svg)](https://www.jitpack.io/#NamelessPeople/RoundLabelTextView)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        compile 'com.github.NamelessPeople:RoundLabelTextView:1.0'
	}
