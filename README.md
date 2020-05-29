RoundLabelTextView
==============================================================

![image](https://github.com/NamelessPeople/RoundLabelTextView/blob/master/srceenshots/screen2.png)

#example

	<com.github.namelesspeople.roundlabeltextview.RoundLabelTextView
		android:id="@+id/round_text"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		app:angle="topLeft"
		app:contentMarginBottom="0dp"
		app:contentMarginStart="0dp"
		app:contentText="@string/content_name"
		app:contentTextColor="#ffffff"
		app:textFlip="true"
		app:contentTextSize="15sp"
		app:labelLength="100dp"
		app:radius="0dp"
		app:topMarginBottom="0dp"	
		app:topMarginStart="0dp"
		app:topText="@string/top_name"
		app:topTextColor="#ffffff" />
		
	1.该变量控制label的方向
	2.标签在第三第四象限的时候，文字处于颠倒状态，设置textFlip="true"文字旋转180度，textFlip="false"不旋转；
	3.Margin可以为+-；
	4.label大小由labelLength来决定；

	
[![](https://www.jitpack.io/v/NamelessPeople/RoundLabelTextView.svg)](https://www.jitpack.io/#NamelessPeople/RoundLabelTextView)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	     implementation 'com.github.NamelessPeople:RoundLabelTextView:1.1'
	}
