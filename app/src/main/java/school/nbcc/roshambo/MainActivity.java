package school.nbcc.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Roshambo
 * Shaelyn Hooley
 * 3/20/19
 */
public class MainActivity extends AppCompatActivity {

    ImageView imgNone;
    ImageView imgRock;
    ImageView imgScissors;
    ImageView imgPaper;
    ImageView imgGameNone;
    ImageView imgGameRock;
    ImageView imgGameScissors;
    ImageView imgGamePaper;

    Rochambo rochambo = new Rochambo();
    TextView txtResult;

    public ImageView player;
    public ImageView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (TextView) findViewById(R.id.txtResult);
        imgNone = (ImageView) findViewById(R.id.imgNone);
        imgRock = (ImageView) findViewById(R.id.imgRock);
        imgScissors = (ImageView) findViewById(R.id.imgScissors);
        imgPaper = (ImageView) findViewById(R.id.imgPaper);

        imgGameNone = (ImageView) findViewById(R.id.imgGameNone);
        imgGameRock = (ImageView) findViewById(R.id.imgGameRock);
        imgGameScissors = (ImageView) findViewById(R.id.imgGameScissors);
        imgGamePaper = (ImageView) findViewById(R.id.imgGamePaper);

        //call method to make sure there is no image selected yet
        clear();
    }

    /*
    Handles when user clicks the rock button option
     */
    public void rockChosen(View view) {
        //displays the rock image
        imgRock.setVisibility(View.VISIBLE);
        imgScissors.setVisibility(View.INVISIBLE);
        imgPaper.setVisibility(View.INVISIBLE);
        imgNone.setVisibility(View.INVISIBLE);

        //sets player to the rock image
        player = imgRock;

        //calls methods in the rochambo class, shows the result in the textview
        rochambo.playerMakesMove(0);
        rochambo.getPlayerMove();
        rochambo.getGameMove();
        txtResult.setText(rochambo.winLoseOrDraw());
        rochambo.winLoseOrDraw();

        //calls method to set game image and animate results
        check();
        animate();
    }

    /*
    Handles when the user clicks the paper button
     */
    public void paperChosen(View view) {
        //displays the paper image
        imgPaper.setVisibility(View.VISIBLE);
        imgScissors.setVisibility(View.INVISIBLE);
        imgRock.setVisibility(View.INVISIBLE);
        imgNone.setVisibility(View.INVISIBLE);

        //sets player to the paper image
        player = imgPaper;

        //calls methods in the rochambo class, shows the result in the textview
        rochambo.playerMakesMove(1);
        rochambo.getPlayerMove();
        rochambo.getGameMove();
        txtResult.setText(rochambo.winLoseOrDraw());
        rochambo.winLoseOrDraw();

        //calls method to set game image and animate results
        check();
        animate();
    }

    public void scissorsChosen(View view) {
        //shows the scissors image for the users choice
        imgScissors.setVisibility(View.VISIBLE);
        imgRock.setVisibility(View.INVISIBLE);
        imgPaper.setVisibility(View.INVISIBLE);
        imgNone.setVisibility(View.INVISIBLE);

        //sets player to the scissors image
        player = imgScissors;

        //calls methods in the rochambo class, shows the result in the textview
        rochambo.playerMakesMove(2);
        rochambo.getPlayerMove();
        rochambo.getGameMove();
        txtResult.setText(rochambo.winLoseOrDraw());
        rochambo.winLoseOrDraw();

        //calls method to set game image and animate results
        check();
        animate();
    }

    public void clear() {
        //resets all images
        imgScissors.setVisibility(View.INVISIBLE);
        imgRock.setVisibility(View.INVISIBLE);
        imgPaper.setVisibility(View.INVISIBLE);

        imgGameScissors.setVisibility(View.INVISIBLE);
        imgGameRock.setVisibility(View.INVISIBLE);
        imgGamePaper.setVisibility(View.INVISIBLE);
    }

    /**
     * Gets the proper images for the game choice,
     * sets game for animation,
     * then animates the result
     */
    public void check() {
        if (rochambo.getGameMove() == 0) {
            //rock
            imgGameRock.setVisibility(View.VISIBLE);
            imgGameScissors.setVisibility(View.INVISIBLE);
            imgGamePaper.setVisibility(View.INVISIBLE);
            imgGameNone.setVisibility(View.INVISIBLE);

            game = imgGameRock;

            animate();
        } else if (rochambo.getGameMove() == 1) {
            //paper
            imgGameRock.setVisibility(View.INVISIBLE);
            imgGameScissors.setVisibility(View.INVISIBLE);
            imgGamePaper.setVisibility(View.VISIBLE);
            imgGameNone.setVisibility(View.INVISIBLE);

            game = imgGamePaper;

            animate();
        } else if (rochambo.getGameMove() == 2) {
            //scissor
            imgGameRock.setVisibility(View.INVISIBLE);
            imgGameScissors.setVisibility(View.VISIBLE);
            imgGamePaper.setVisibility(View.INVISIBLE);
            imgGameNone.setVisibility(View.INVISIBLE);

            game = imgGameScissors;

            animate();
        } else if (rochambo.getGameMove() == 3) {
            //none
            imgGameRock.setVisibility(View.INVISIBLE);
            imgGameScissors.setVisibility(View.INVISIBLE);
            imgGamePaper.setVisibility(View.INVISIBLE);
            imgGameNone.setVisibility(View.VISIBLE);
        }

    }

    /*Using animation example given*/
    public void animate() {
        player.setRotationX(0f);

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(game,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }

}

