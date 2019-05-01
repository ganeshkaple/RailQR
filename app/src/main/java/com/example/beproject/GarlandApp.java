/*
package com.example.beproject;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;




*/
/**
 * Temporary Class to generate dummy data, To be removed once actual data comes Extends
 * AppController Copied From a com.ramotion.garlandview library
 *
 * @author not me
 * <p>
 * This is a listner, I don't know what it does
 * <p>
 * The Faker Object, which generates fake data as per requirement
 * <p>
 * This is called as soon as the activity runs, it initializes Faker object & calls it's parent
 * onCreate()
 * <p>
 * Initializes the faker object, It is then used to generate fake or temporary data,
 * <p>
 * Uses RxJava Subscriber pattern to get single value at a time  on Faker object
 * <p>
 * <p>
 * this is used to attach listeners to this class it calls onFakerReady, whenever the Faker
 * object is initialized
 * @param listener it uses the listener pattern for its functionality
 * <p>
 * this is used to remove listeners to this class it removes any listeners attached to it
 * @param listener it uses the listener pattern for its functionality
 * <p>
 * This is interface of FakerReadyListener, Needs to be implemented by any class which
 * want's to
 * use this
 * <p>
 * this methods gets called when the faker object is ready to use, & needs to be
 * implemented
 * to perform operations according to it
 * @param faker object of the Faker class
 *//*

public class GarlandApp extends App {
	*/
/**
 * This is a listner, I don't know what it does
 *//*


	private final List<WeakReference<FakerReadyListener>> mListeners = new ArrayList<>();
	*/
/**
 * The Faker Object, which generates fake data as per requirement
 *//*

	private Faker mFaker;

	*/
/**
 * This is called as soon as the activity runs, it initializes Faker object & calls it's parent
 * onCreate()
 *//*

	@Override
	public void onCreate() {
		super.onCreate();
		//	initFaker();
	}

	*/
/**
 * Initializes the faker object, It is then used to generate fake or temporary data,
 *//*

	@SuppressLint("CheckResult")
	private void initFaker() {
		*/
/**
 * Uses RxJava Subscriber pattern to get single value at a time  on Faker object
 *
 *//*


		Single.create( e -> {
			final Faker faker = new Faker();

			if (!e.isUnsubscribed()) {
				e.onSuccess(faker);
			}
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(faker ->{
					mFaker = (Faker) faker;

					for (WeakReference<FakerReadyListener> wRef : mListeners) {
						final FakerReadyListener listener = wRef.get();
						if (listener != null) {
							listener.onFakerReady(mFaker);
						}
					}
				});

	}

	*/
/**
 * this is used to attach listeners to this class it calls onFakerReady, whenever the Faker
 * object is initialized
 *
 * @param listener it uses the listener pattern for its functionality
 *//*

	public void addListener(@NonNull FakerReadyListener listener) {
		mListeners.add(new WeakReference<>(listener));
		if (mFaker != null) {
			listener.onFakerReady(mFaker);
		}
	}

	*/
/**
 * this is used to remove listeners to this class it removes any listeners attached to it
 *
 * @param listener it uses the listener pattern for its functionality
 *//*

	public void removeListener(FakerReadyListener listener) {
		for (WeakReference<FakerReadyListener> wRef : mListeners) {
			if (wRef.get() == listener) {
				mListeners.remove(wRef);
				break;
			}
		}
	}

	*/
/**
 * This is interface of FakerReadyListener, Needs to be implemented by any class which
 * want's to
 * use this
 *//*

	public interface FakerReadyListener {
		*/
/**
 * this methods gets called when the faker object is ready to use, & needs to be
 * implemented
 * to perform operations according to it
 *
 * @param faker object of the Faker class
 *//*

		void onFakerReady(Faker faker);
	}

}
*/
