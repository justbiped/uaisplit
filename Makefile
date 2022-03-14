.PHONY:

android_tests:
	./gradlew prepareAndroidTest
	chmod +x android-test.sh
	./android-test.sh
