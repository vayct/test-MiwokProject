package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) ||
                            (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
                        releaseMediaPlayer();


                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                        mMediaPlayer.start();

                }
            };




    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            //release audio focus when not needed
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list , container, false);



        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?","minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinne oyaase'ne", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","michekses?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "eenes'aa", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming?","hee' eenem", R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming.","eenem", R.raw.phrase_im_coming));
        words.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here", "enni'nem", R.raw.phrase_come_here));



        WordAdapter numbersAdapter =
                new WordAdapter(getActivity(), words, R.color.category_phrases);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(numbersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //have audio focus
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getSoundResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}
