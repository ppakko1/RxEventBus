[![](https://jitpack.io/v/ppakko1/RxEventBus.svg)](https://jitpack.io/#ppakko1/RxEventBus)

## RxEventBus
EventBus for Android
<br/>

## gradle
### 1. Add it in your root build.gradle at the end of repositories
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

### 2. Add th dependency

	dependencies {
		compile 'com.github.ppakko1:RxEventBus:1.0.0'
	}
<br/>



## How to
### Post
	RxEventBus.getInstance().post(new Event());
	
### Subscribe
	RxEventBus.getInstance()
            .event(Event.class)
            .compose(bindUntilEvent(ActivityEvent.DESTROY))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(event1 -> {
                textView.setText(event1.getText());
            }, Throwable::printStackTrace);
<br/>

            
## Impotance
### Should be manage lifecycle
##### Method 1. use https://github.com/trello/RxLifecycle
    RxEventBus.getInstance()
                .event(Event.class)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event1 -> {
                    textView.setText(event1.getText());
                }, Throwable::printStackTrace);


##### Method 2. use dispose()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disposable = SubscripRxEventBus.getInstance()
                .event(Event.class)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event1 -> {
                    textView.setText(event1.getText());
                }, Throwable::printStackTrace);
    }
<br/>

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
<br/>

## License

    MIT License
    
    Copyright (c) 2017 Park jinwoo
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

