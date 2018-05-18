package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import rodrigo.example.com.bitmapfromxmllayout.R;

public class SoundActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    private final int DURATION = 3; // seconds.
    private final int SAMPLE_RATE = 2000;
    private final int SAMPLES_NUMBER = DURATION * SAMPLE_RATE;
    private final double SAMPLE[] = new double[SAMPLES_NUMBER];

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private final byte mGeneratedSound[] = new byte[2 * SAMPLES_NUMBER];
    private Handler mHandler = new Handler();

//    http://3.bp.blogspot.com/_k5-Jsx3qdWI/TFIpsEhqJOI/AAAAAAAAAGc/hbXCY5825Z0/s1600/notas.jpg

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        musicalTone(82.4, 2000);
//        musicalTone(110.0, 2000);
//        musicalTone(146.8, 2000);
//        musicalTone(196.0, 2000);
//        musicalTone(246.9, 2000);
//        musicalTone(329.6, 2000);

//        musicalTone(622.3, 2000);
        musicalTone(311.15, 2000);
//        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, (int)(ToneGenerator.MAX_VOLUME * 0.85));
//        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void musicalTone(final Double frequency, final Integer delay) {
        // Use a new tread as this can take a while
        Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone(frequency);
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        playSound();
                    }
                }, delay);
            }
        });
        thread.start();
    }

    private void genTone(Double frequency) {
        // Fill out the array.
        for (int i = 0; i < SAMPLES_NUMBER; ++i) {
            SAMPLE[i] = Math.sin(2 * Math.PI * i / (SAMPLE_RATE / frequency));
        }

        // Convert to 16 bit pcm sound array assumes the SAMPLE buffer is normalised.
        int idx = 0;
        for (double dVal : SAMPLE) {
            short val = (short) (dVal * 32767);
            mGeneratedSound[idx++] = (byte) (val & 0x00ff);
            mGeneratedSound[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
    }

    @SuppressWarnings("deprecation")
    void playSound() {
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
            8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
            AudioFormat.ENCODING_PCM_16BIT, SAMPLES_NUMBER,
            AudioTrack.MODE_STATIC);
        audioTrack.write(mGeneratedSound, 0, SAMPLES_NUMBER);
        audioTrack.play();
    }
}