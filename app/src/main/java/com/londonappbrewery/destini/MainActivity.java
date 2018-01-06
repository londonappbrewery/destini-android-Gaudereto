package com.londonappbrewery.destini;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mStoryTextView;
    private Button mButtonTop;
    private Button mButtonBottom;

    private int mIndex;
    private int mStoryIndex;

    private int[] storyT3 = new int[] {
            R.string.T3_Story,
            R.string.T3_Ans1,
            R.string.T3_Ans2
    };

    private int[] storyT2 = new int[] {
            R.string.T2_Story,
            R.string.T2_Ans1,
            R.string.T2_Ans2
    };

    private int mPreviousButton = 0; // 1 -> top 2-> bottom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mStoryIndex = savedInstanceState.getInt("StoryIndexKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mPreviousButton = savedInstanceState.getInt("PreviousButtonKey");
        } else {
            mStoryIndex = 0;
            mIndex = 0;
            mPreviousButton = 0;
        }

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop     = (Button)   findViewById(R.id.buttonTop);
        mButtonBottom  = (Button)   findViewById(R.id.buttonBottom);

        if (mStoryIndex == 2) {
            updateStory(storyT2);
        } else if (mStoryIndex == 3) {
            updateStory(storyT3);
        } else if (mStoryIndex == 4) {
            endOfStory(R.string.T4_End);
        } else if (mStoryIndex == 5) {
            endOfStory(R.string.T5_End);
        } else if (mStoryIndex == 6) {
            endOfStory(R.string.T6_End);
        }

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventButtonTop();
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventButtonBottom();
            }
        });
    }

    private void eventButtonTop() {
        mIndex = mIndex + 1;
        if (mIndex == 1){
            updateStory(storyT3);
            mStoryIndex = 3;

        } else if ( mIndex == 2 ) {
            if (mPreviousButton == 1) {
                endOfStory(R.string.T6_End);
                mStoryIndex = 6;

            } else {
                updateStory(storyT3);
                mStoryIndex = 3;
            }
        }
        else if (mIndex == 3) {
            endOfStory(R.string.T6_End);
            mStoryIndex = 6;
        }
        mPreviousButton = 1;
    }

    private void eventButtonBottom() {
        mIndex = mIndex + 1;
        if (mIndex == 1){
            updateStory(storyT2);
            mStoryIndex = 2;
        } else if (mIndex == 2) {
            if (mPreviousButton == 1) {
                endOfStory(R.string.T5_End);
                mStoryIndex = 5;
            } else {
                endOfStory(R.string.T4_End);
                mStoryIndex = 4;
            }
        } else if (mIndex == 3) {
            endOfStory(R.string.T5_End);
            mStoryIndex = 5;
        }
        mPreviousButton = 2;
    }
    private void updateStory(int[] story) {
        mStoryTextView.setText(story[0]);
        mButtonTop.setText(story[1]);
        mButtonBottom.setText(story[2]);
    }

    private void endOfStory(int finalStory) {
        mStoryTextView.setText(finalStory);
        mButtonTop.setVisibility(View.GONE);
        mButtonBottom.setVisibility(View.GONE);
        dialogCall();

    }

    private void dialogCall() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Game Over");
        alert.setCancelable(false);
        alert.setMessage("You reached the end! Would you like to try again or finish the app" );
        alert.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setNegativeButton("Close Application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();            }
        });
        alert.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryIndexKey",mStoryIndex);
        outState.putInt("IndexKey",mIndex);
        outState.putInt("PreviousButtonKey",mPreviousButton);
    }

}

