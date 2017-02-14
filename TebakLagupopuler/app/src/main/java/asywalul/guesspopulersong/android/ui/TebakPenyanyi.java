package asywalul.guesspopulersong.android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import asywalul.guesspopulersong.android.R;
import asywalul.guesspopulersong.android.data.ConstPenyanyi;
import asywalul.guesspopulersong.android.model.DialogButtonClickListener;
import asywalul.guesspopulersong.android.model.Music;
import asywalul.guesspopulersong.android.model.WordButton;
import asywalul.guesspopulersong.android.model.WordButtonClickListener;
import asywalul.guesspopulersong.android.model.WordGridView;
import asywalul.guesspopulersong.android.util.SoundPlayUtil;
import asywalul.guesspopulersong.android.util.Util;
import asywalul.guesspopulersong.android.util.ViewUtil;


public class TebakPenyanyi extends AppCompatActivity implements WordButtonClickListener {

    private static final int WORDS_COUNT = 24;
    private static final int SPASH_TIMES = 7;

    private static final int STATUS_ANSWER_RIGHT = 1;
    private static final int STATUS_ANSWER_WRONG = 2;
    private static final int STATUS_ANSWER_LACK = 3;

    private static final int ID_DIALOG_DEL_ANSWER = 5;
    private static final int ID_DIALOG_LACK_COINS = 6;

    public final static int ID_DIALOG_DELETE_WORD=7; //删除错误答案
    public final static int ID_DIALOG_TIP_ANSWER=8; //答案提示
    public final static int ID_DIALOG_LACK_COIN=9; //金币不足


    private Animation mPanAnim;
    private LinearInterpolator mPanLin;
    private Animation mBarInAnim;
    private LinearInterpolator mBarInLin;

    private LinearLayout mPassView;

    private Animation mBarOutAnim;
    private LinearInterpolator mBarOutLin;

    //play button
    private ImageButton mBtnPlayStart;

    private ImageButton mBtnAddCoins;

    private ImageView mViewPan;
    private ImageView mViewPanBar;

    private boolean mIsRunning;

    private Music mCurrentMusic;
    private int mCurrentIndex = 0;

    private ArrayList<WordButton> mAllWords;
    private WordGridView mGridView;

    private ArrayList<WordButton> mSelectedWords;
    private LinearLayout mViewWordsContainer;

    private int mCurrentCoins;

    private TextView mViewCoins;

    private TextView mViewPassPercent;

    private TextView mViewGuessMode;

    private TextView mViewCurrentStage;
    private TextView mViewCurrentStagePass;

    private TextView mViewCurrentMusicName;

    private ImageView mViewDelete;
    private Button next;
    private ImageView mViewTip;
    private AdView mAdView;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next               = (Button)findViewById(R.id.next);
        mPassView          = (LinearLayout) findViewById(R.id.pass_view);


        sp = getSharedPreferences("configpenyanyi", MODE_PRIVATE);
        mCurrentCoins = Integer.parseInt(sp.getString("coins", "200"));

        if(!ConstPenyanyi.hasMoreMusic(mCurrentIndex)) {
            mCurrentIndex = Integer.parseInt(sp.getString("undonepenyanyi", "0"));
        }else {
            mCurrentIndex = Integer.parseInt(sp.getString("donepenyanyi", "0"));
        }

        if (!ConstPenyanyi.hasMoreMusic(mCurrentIndex)) {
            startActivity(new Intent(TebakPenyanyi.this, PassActivityJudul.class));
            return;
        }

        mAdView             = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mViewPan            = (ImageView) findViewById(R.id.iv_pan);
        mViewPanBar         = (ImageView) findViewById(R.id.iv_pan_bar);
        mGridView           = (WordGridView) findViewById(R.id.gridview);
        mViewWordsContainer = (LinearLayout) findViewById(R.id.word_select_container);

        findViewById(R.id.btn_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mViewCoins = (TextView) findViewById(R.id.txt_bar_icon);
        mViewGuessMode = (TextView) findViewById(R.id.tv_mode);

        mViewCoins.setText(mCurrentCoins + "");

        mViewDelete = (ImageView) findViewById(R.id.btn_delete_word);
        mViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteWord();
            }
        });
        mViewTip = (ImageView) findViewById(R.id.btn_tip_answer);
        mViewTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentCoins<50){
                    Toast.makeText(TebakPenyanyi.this,getString(R.string.text_point_kurang), Toast.LENGTH_LONG).show();
                }else {
                    tipAnswer();
                }

            }
        });


        mGridView.setOnWordButtonClickedListener(this);

        initAnims();

        mBtnPlayStart = (ImageButton) findViewById(R.id.btn_play_start);
        mBtnPlayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePlay();
            }
        });

        initCurrentStageData();

        for (int i = 0; i < WORDS_COUNT; i++) {
            toRand.add(i);
        }

        LinearLayout mm  = (LinearLayout) findViewById(R.id.layout_bar_coin);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentCoins > 150){
                    Toast.makeText(TebakPenyanyi.this,getString(R.string.text_point_bisa_ditambah), Toast.LENGTH_LONG).show();

                }else {
                    dealCoins(50);
                    Intent newintent = new Intent(TebakPenyanyi.this,InterestialLogout.class);
                    startActivity(newintent);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePassEvent("undonepenyanyi");
                mViewDelete.setVisibility(View.VISIBLE);
            }
        });

    }

    private void showConfirmDialog(int d,DialogButtonClickListener listener){
        switch (d){
            case ID_DIALOG_DELETE_WORD:
                Util.showDialog(TebakPenyanyi.this, getString(R.string.text_hapus_balok_huruf),listener);
                break;

            case ID_DIALOG_TIP_ANSWER:
                Util.showDialog(TebakPenyanyi.this,getString(R.string.text_lihat_huruf),listener);
                break;

            case ID_DIALOG_LACK_COIN:
                Toast.makeText(TebakPenyanyi.this,getString(R.string.text_emas_kurang), Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void deleteWord() {

        showConfirmDialog(ID_DIALOG_DELETE_WORD, new DialogButtonClickListener() {
            @Override
            public void onClick() {
                WordButton btn = findNoteAnswerWord();
                if (btn != null) {
                    setButtonVisiable(btn, View.INVISIBLE);
                }
                dealCoins(-getResources().getInteger(R.integer.pay_delete_answer));
                setDeleteconfig(View.INVISIBLE);
                deleteOneWord();
                //删除按钮隐藏
                setDeleteconfig(View.INVISIBLE);
            }
        });
    }

    private void tipAnswer() {
        showConfirmDialog(ID_DIALOG_TIP_ANSWER, new DialogButtonClickListener() {
            @Override
            public void onClick() {
                char[] name = mCurrentMusic.getNameArray();
                int pos = new Random().nextInt(name.length);
                // Toast.makeText(TebakJudul.this, getString(R.string.text_ada_huruf) + name[pos], Toast.LENGTH_LONG).show();
                dealCoins(-getResources().getInteger(R.integer.pay_tip_answer));
                tipAnswer2();
            }
        });
    }

    private void deleteOneWord(){
        // 减少金币

        for (int i=0;i<=ConstPenyanyi.DELETETIME;i++) {
            if (findNoteAnswerWord() != null) {
                setButtonVisiable(findNoteAnswerWord(), View.INVISIBLE);
            }
        }
    }

    private void setDeleteconfig(int vis){
        //按钮
        ImageButton button = (ImageButton) findViewById(R.id.btn_delete_word);
        button.setVisibility(vis);
        //文字
        // TextView textView = (TextView) findViewById(R.id.delete_text);
        //  textView.setVisibility(vis);
        //图形
        // ImageView view= (ImageView) findViewById(R.id.delete_coin);
        // view.setVisibility(vis);

    }

    private void tipAnswer2(){
        // 减少金币数量
        boolean tipWord=false;
        for (int i=0;i<mSelectedWords.size();i++){
            if (mSelectedWords.get(i).mWordString.length()==0){

                onWordButtonClick(findIsAnswerWord(i));
                tipWord=true;
                break;
            }
        }
        if (!tipWord){
            sparkWords();
        }

    }
    private boolean handleCoins(int data){
        if (mCurrentCoins + data>=0){
            mCurrentCoins+=data;
            mViewCoins.setText(mCurrentCoins+"");
            return true;
        }
        else {return false;}
    }
    private int getDeleteWordCoin(){
        return this.getResources().getInteger(R.integer.play_delete_answer);
    }
    private int getTipCoin(){
        return this.getResources().getInteger(R.integer.play_tip_answer);
    }

    private List<Integer> toRand = new LinkedList<Integer>();


    private WordButton findNoteAnswerWord(){

        Random random = new Random();
        WordButton buf = null;
        //if (mDelNum <( MyGridView.COUNTS_WORDS)-mCurrentSong.getNameLength()) {
        List<WordButton> btns = findVisibleWordButton();
        if (btns.size() == 1) {
            buf = null;
        } else {
            buf = btns.get(random.nextInt(btns.size()));
        }

        return buf;
    }

    private List<WordButton> findVisibleWordButton() {
        List<WordButton> wordBtns = new ArrayList<WordButton>();

        for (WordButton btn : mAllWords) {
            if (btn.mIsVisiable && !isTheAnswerWord(btn)) {
                wordBtns.add(btn);
            }
        }

        return wordBtns;
    }

    private boolean isTheAnswerWord(WordButton word){
        boolean result =false;
        for (int i=0;i<mCurrentMusic.getNameLength();i++){
            if (word.mWordString.equals
                    (""+mCurrentMusic.getNameArray()[i])){
                result =true;
                break;
            }
        }
        return result;
    }

    private WordButton findIsAnswerWord(int index){
        WordButton buf = null;
        for(int i=0;i<WordGridView.COUNTS_WORDS;i++){
            buf=mAllWords.get(i);
            if (buf.mWordString.equals(""+mCurrentMusic.getNameArray()[index])){
                return buf;
            }
        }
        return null;

    }

    private void dealCoins(int count) {
        mCurrentCoins += count;
        SharedPreferences.Editor et = sp.edit();
        et.putString("coins", mCurrentCoins + "");
        et.commit();
        mViewCoins.setText(mCurrentCoins + "");
    }



    private void initAnims() {
        mPanAnim = AnimationUtils.loadAnimation(TebakPenyanyi.this, R.anim.rotate);
        mPanLin = new LinearInterpolator();
        mPanAnim.setInterpolator(mPanLin);
        mPanAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                SoundPlayUtil.playMusic(TebakPenyanyi.this, mCurrentMusic.getFilename());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("pan rotate");
                mViewPanBar.startAnimation(mBarOutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mBarInAnim = AnimationUtils.loadAnimation(TebakPenyanyi.this, R.anim.rotate_45);
        mBarInLin = new LinearInterpolator();
        mBarInAnim.setFillAfter(true);
        mBarInAnim.setInterpolator(mBarInLin);
        mBarInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("in end");
                mViewPan.startAnimation(mPanAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mBarOutAnim = AnimationUtils.loadAnimation(TebakPenyanyi.this, R.anim.rotate_d_45);
        mBarOutLin = new LinearInterpolator();
        mBarOutAnim.setFillAfter(true);
        mBarOutAnim.setInterpolator(mBarOutLin);
        mBarOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("out end");
                mIsRunning = false;
                mBtnPlayStart.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onWordButtonClick(WordButton btn) {
        setSelectedWord(btn);
        switch (checkAnswer()) {
            case STATUS_ANSWER_RIGHT:
                handlePassEvent("donepenyanyi");
                break;
            case STATUS_ANSWER_WRONG:
                sparkWords();
                Toast.makeText(this, getString(R.string.text_wrong_answer), Toast.LENGTH_SHORT).show();
                break;
            case STATUS_ANSWER_LACK:
                for (int i = 0; i < mSelectedWords.size(); i++)
                    mSelectedWords.get(i).mViewButton.setTextColor(Color.WHITE);
                break;
        }
    }

    private void handlePassEvent(String done) {

        if(done.equals("donepenyanyi")){
            mViewPan.clearAnimation();
            SoundPlayUtil.pause(TebakPenyanyi.this);
            mViewDelete.setVisibility(View.VISIBLE);
            SoundPlayUtil.playTone(TebakPenyanyi.this, SoundPlayUtil.INDEX_STONE_COIN);

            mViewCurrentStagePass = (TextView) findViewById(R.id.text_current_stage_pass);
            mViewCurrentStagePass.setText(mCurrentIndex + "");

            mViewCurrentMusicName = (TextView) findViewById(R.id.text_currnt_song_name);
            mViewCurrentMusicName.setText(mCurrentMusic.getMusicName());

            Button btnPass = (Button) findViewById(R.id.btn_next);
            btnPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!!ConstPenyanyi.hasMoreMusic(mCurrentIndex)) {
                        startActivity(new Intent(TebakPenyanyi.this, PassActivityPenyanyi.class));
//
                        return;
                    }else {
                        mPassView.setVisibility(View.INVISIBLE);
                        initCurrentStageData();
                    }
                    //oijoijoijo

                }
            });
            ImageButton btnShare = (ImageButton) findViewById(R.id.btn_share);
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TebakPenyanyi.this, "share to wechat.", Toast.LENGTH_SHORT).show();
                }
            });

            dealCoins(50);
            ((TextView)findViewById(R.id.txt_main_achievement)).setText(getString(R.string.text_selesai) + mCurrentIndex + "/" + ConstPenyanyi.SONG_INFO.length);
            mPassView.setVisibility(View.VISIBLE);

            SharedPreferences.Editor et = sp.edit();
            et.putString(done, "" + mCurrentIndex);
            et.commit();
        }else {
            SharedPreferences.Editor et = sp.edit();
            et.putString(done, "" + mCurrentIndex);
            et.commit();
            initCurrentStageData();

        }




    }

    /**
     * JAWABAN SALAH DICEK
     */
    private void sparkWords() {
        TimerTask task = new TimerTask() {
            boolean mChange;
            int mSpardTimes;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mSpardTimes++ > SPASH_TIMES)
                            return;
                        for (int i = 0; i < mSelectedWords.size(); i++) {
                            mSelectedWords.get(i).mViewButton.setTextColor(mChange ? Color.RED : Color.WHITE);
                        }
                        mChange = !mChange;
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1, 150);
    }

    private int checkAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < mSelectedWords.size(); i++) {
            WordButton btn = mSelectedWords.get(i);
            if (TextUtils.isEmpty(btn.mWordString))
                return STATUS_ANSWER_LACK;
            answer.append(btn.mWordString);
        }
        return answer.toString().equals(mCurrentMusic.getMusicName())
                ? STATUS_ANSWER_RIGHT : STATUS_ANSWER_WRONG;
    }

    private void clearAnswer(WordButton button) {
        button.mViewButton.setText("");
        button.mWordString = "";
        setButtonVisiable(mAllWords.get(button.mIndex), View.VISIBLE);
        for (int i = 0; i < mSelectedWords.size(); i++)
            mSelectedWords.get(i).mViewButton.setTextColor(Color.WHITE);
    }

    private void setSelectedWord(WordButton button) {

        for (int i = 0; i < mSelectedWords.size(); i++) {
            if (TextUtils.isEmpty(mSelectedWords.get(i).mWordString)) {
                mSelectedWords.get(i).mViewButton.setText(button.mWordString + "");
                mSelectedWords.get(i).mIsVisiable = true;
                mSelectedWords.get(i).mWordString = button.mWordString;
                mSelectedWords.get(i).mIndex = button.mIndex;

                setButtonVisiable(button, View.INVISIBLE);
                break;
            }
        }
    }

    private void setButtonVisiable(WordButton button, int visibility) {
        button.mViewButton.setVisibility(visibility);
        button.mIsVisiable = (visibility == View.VISIBLE);
    }


    @Deprecated
    private Music loadStageMusicInfo(int index) {
        Music music = new Music();
        String[] infos = ConstPenyanyi.SONG_INFO[index];
        music.setFilename(infos[0]);
        music.setMode(Integer.parseInt(infos[1]));
        music.setMusicName(infos[2]);
        return music;
    }

    private void initCurrentStageData() {

        if (!ConstPenyanyi.hasMoreMusic(mCurrentIndex)) {
            startActivity(new Intent(TebakPenyanyi.this, TebakPenyanyi.class));
            return;
        }else {

            mCurrentMusic = loadStageMusicInfo(mCurrentIndex);
            Log.i("duxu", "NAMA Lgu:" + mCurrentMusic.getMusicName());
            mSelectedWords = initSelectedWords();
            // Check if we're running on Android 5.0 or higher


            LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f);


            mViewWordsContainer.removeAllViews();

            for (WordButton btn : mSelectedWords) {
                mViewWordsContainer.addView(btn.mViewButton, params);
            }

            mViewCurrentStage = (TextView) findViewById(R.id.txt_current_stage);
            mViewCurrentStage.setText((mCurrentIndex + 1) + "");

            mAllWords = initAllWords();
            mGridView.updateData(mAllWords);

            mViewGuessMode.setText(Music.getModeString(mCurrentMusic.getMode()));

            mCurrentIndex++;
            handlePlay();
        }
    }

    /**
     * 初始化已选择文字框
     */
    private ArrayList<WordButton> initSelectedWords() {
        ArrayList<WordButton> data = new ArrayList<WordButton>();
        for (int i = 0; i < mCurrentMusic.getNameLength(); i++) {
            View v = ViewUtil.getView(TebakPenyanyi.this, R.layout.gridview_item);
            final WordButton holder = new WordButton();
            holder.mViewButton = (android.widget.Button) v.findViewById(R.id.item_btn);
            holder.mViewButton.setTextColor(Color.WHITE);
            holder.mViewButton.setText("");
            holder.mIsVisiable = false;
            holder.mIndex = 1;
            holder.mViewButton.setBackgroundResource(R.drawable.game_wordblank);

            holder.mViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearAnswer(holder);
                }
            });
            data.add(holder);
        }
        return data;
    }

    /**
     * 初始化待选择文字框
     */
    private ArrayList<WordButton> initAllWords() {
        ArrayList<WordButton> data = new ArrayList<WordButton>(WORDS_COUNT);
        char[] words = generateWords();
        for (int i = 0; i < WORDS_COUNT; i++) {
            WordButton button = new WordButton();
            button.mWordString = words[i] + "";
            data.add(button);
        }
        return data;
    }

    private void handlePlay() {
        if (mIsRunning) {
            return;
        }
        mIsRunning = true;
        mViewPanBar.startAnimation(mBarInAnim);
        mBtnPlayStart.setVisibility(View.INVISIBLE);
    }

    private char[] generateWords() {
        char[] words = new char[WORDS_COUNT];
        for (int i = 0; i < mCurrentMusic.getNameLength(); i++) {
            words[i] = mCurrentMusic.getNameArray()[i];
        }
        //TODO 初始化随机生成文字（重复）
        for (int i = mCurrentMusic.getNameLength(); i < WORDS_COUNT; i++) {
            words[i] = getRandomChar();
        }
        Random r = new Random();
        for (int i = WORDS_COUNT - 1; i >= 0; i--) {
            int index = r.nextInt(i + 1);
            char buf = words[index];
            words[index] = words[i];
            words[i] = buf;
        }
        return words;
    }

    private char getRandomChar() {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123";
        final int N = alphabet.length();

        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            System.out.print(alphabet.charAt(r.nextInt(N)));
        }

        return alphabet.charAt(r.nextInt(N));
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            mViewPan.clearAnimation();
            SoundPlayUtil.pause(TebakPenyanyi.this);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(TebakPenyanyi.this,GuideActivity.class);
        startActivity(i);
    }

    // AUTO GEN CODES .
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
