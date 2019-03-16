package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /*
    This listener gets triggered when the MediaPlayer has completed
    playing the audio file, so you can reuse this listener instead of
    constantly creating new instances
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father,
                R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother,
                R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son,
                R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter,
                R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.family_grandfather));

        /*
        Creates an {@link ArrayAdapter}, whose data source is a list of Strings. The
        adapter knows how to create layouts for each item in the list, using the
        simple_list_item_1.xml layout resource defined in the Android framework.
        This list item layout contains a single {@link TextView}, which the adapter
        will set to display a single word.
        */
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);
        //the second parameter is a resource file, in this case it was predefined for us by the Android framework
        //the last parameter is where we get our data source
        /*
        Finds the {@link ListView} object in the view hierarchy of the {@link Activity}.
        There should be a {@link ListView} with the view ID called list, which is declared in
        the word_listyout file.
         */
        ListView listView = (ListView) findViewById(R.id.list);
        /*
        Makes the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        {@link ListView} will display list items for each word in the list of words.
        Do this by calling the setAdapter method on the {@link ListView} object and pass in
        1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
         */
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Because the clickListener is defined in line (in this case, as an anonymous class),
            //the methods of this in line clickListener can only reference local variables if they are declared final
            //It can also reference global variables in the NumbersActivity class
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //We use the position parameter in the onItemClick() callback to determine which word was clicked on,
                //in order to extract the correct audio resource ID

                //Release the media player if it currently exists because we are about to
                //play a different sound file.

                releaseMediaPlayer();

                //This gets the Word object at the given position the user clicked on
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
                mMediaPlayer.start(); // no need to call prepare(); create() does that for you
                //Setup a listener on the media player, so that we can stop and release the
                //media player once the sounds have finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
