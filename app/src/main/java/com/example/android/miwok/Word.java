package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * Each object contains a default translation and a Miwok translation for that word.
 */
public class Word {

    // Default translation for the word, m stands for member variable
    private String mDefaultTranslation;

    // Miwok translation for the word
    private String mMiwokTranslation;

    // Image resource ID for the word
    // Starts with this value because the default is assuming that there is no image associated with the word
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // NO_IMAGE_PROVIDED is set to -1 because it is out of the range of all the possible valid resource IDs.
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

    // In order to create a new Word object, the caller has to pass in these 2 required inputs
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    // Public getter methods are being used to access private global variables
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceId;
    }

    //Returns whether or not there is an image for this word
    public boolean hasImage() {
        //If mImageResourceID is not equal to -1 (hasImage returns true), then there exists a valid image
        //If mImageResourceID DOES equal NO_IMAGE PROVIDED, then the method returns false.
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){ return mAudioResourceId; }
}
