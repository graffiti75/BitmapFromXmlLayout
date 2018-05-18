package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import rodrigo.example.com.bitmapfromxmllayout.R;

public class SynthActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Thread mThread;
    private int mSource = 44100;
    private boolean mIsRunning = true;
    private SeekBar mSeekBar;
    private double mSliderValue;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synth);

        // point the slider to thwe GUI widget
        mSeekBar = (SeekBar) findViewById(R.id.id_seek_bar);

        // create a touchListener for the slider bar;
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {}

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mSliderValue = progress / (double) seekBar.getMax();
                }
            }
        };

        // Sets the touchListener on the slider.
        mSeekBar.setOnSeekBarChangeListener(listener);

        // Start a new thread to synthesise audio.
        mThread = new Thread() {
            public void run() {
                // Set process priority.
                setPriority(Thread.MAX_PRIORITY);

                // Sets the buffer size.
                int bufferSize = AudioTrack.getMinBufferSize(mSource, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT);

                // Create an audio track object.
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                        mSource, AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, bufferSize,
                    AudioTrack.MODE_STREAM);

                short samples[] = new short[bufferSize];
                int amp = 10000;
                double twopi = 8. * Math.atan(1.);
                double fr;
                double ph = 0.0;

                // Starts audio.
                audioTrack.play();

                // Synthesis loop.
                while (mIsRunning) {
                    fr = 11 + 11 * mSliderValue;
                    for (int i = 0; i < bufferSize; i++) {
                        samples[i] = (short) (amp * Math.sin(ph));
                        ph += twopi * fr / mSource;
                    }
                    audioTrack.write(samples, 0, bufferSize);
                }
                audioTrack.stop();
                audioTrack.release();
            }
        };
        mThread.start();
    }

    public void onDestroy() {
        super.onDestroy();
        mIsRunning = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mThread = null;
    }
}