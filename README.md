# BottomNav

1.与ViewPager联动

2.切换icon与文字渐变效果

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.serenadegx:BottomNav:1.0.0'
	}

use:

 <android.support.v4.view.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>
    <com.example.bottomnavview.BottomNavView
        android:id="@+id/bottom_nav"
        android:background="@android:color/white"
        android:orientation="horizontal"
        android:padding="10dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <com.example.bottomnavview.BottomChildView
            android:id="@+id/main"
            android:checked="true"
            app:check_icon="@mipmap/main_in"
            app:uncheck_icon="@mipmap/main_out"
            android:textColor="@android:color/darker_gray"
            app:check_color="@color/colorBlue"
            android:textSize="14sp"
            android:text="主页"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content" />
        <com.example.bottomnavview.BottomChildView
            android:id="@+id/buy"
            app:check_color="@color/colorBlue"
            app:check_icon="@mipmap/buy_in"
            app:uncheck_icon="@mipmap/buy_out"
            android:textColor="@android:color/darker_gray"
            android:text="购车"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content" />
        <com.example.bottomnavview.BottomChildView
            android:id="@+id/mine"
            app:check_icon="@mipmap/mine_in"
            app:check_color="@color/colorBlue"
            app:uncheck_icon="@mipmap/mine_out"
            android:textColor="@android:color/darker_gray"
            android:layout_weight="1"
            android:text="我的"
            android:textSize="14sp"
            android:layout_width="0dp"
            android:layout_height="wrap_content" />
    </com.example.bottomnavview.BottomNavView>
