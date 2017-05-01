package com.example.setsunagx.groupproject_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;


public class Scenes extends ActionBarActivity implements View.OnClickListener {
    private static final int BAD_END_1 = 0;
    private RelativeLayout background;
    private ImageView leftChar, rightChar;
    private TextView charLines;
    private int scenesNum, shotNum, takeNum;
    private Animation fadeOut;
    private Thread t;
    private static Handler mHandler;
    private String currLine;
    private int choice, tries;
    private Queue<String> autoPlayStr;
    private Queue<int[]> autoPlayImg;

    public Scenes() {
        scenesNum = shotNum = takeNum = 0;
        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(0);
        fadeOut.setDuration(1000);
        fadeOut.setRepeatCount(0);
        choice = tries = 0;
        autoPlayStr = new LinkedList<>();
        autoPlayImg = new LinkedList<>();
        t = null;
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        charLines.setText(msg.obj.toString());
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scene_1);
        getSupportActionBar().hide();
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit to main menu")
                .setMessage("Are you sure you want to quit this game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Scenes.this, MainActivity.class));
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
        /*super.onBackPressed();*/
    }

    @Override
    public void onClick(View v) {
        switch (scenesNum) {
            case 0:
                switch (shotNum) {
                    case 0:
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                        /*findViewById(R.id.background).startAnimation(fadeOut);*/
                        setContentView(view);
                        findViewById(R.id.background).setOnClickListener(Scenes.this);
                        findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                        findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setHeight(800);
                        typing("第一章\n\t遊戲開始", 300);
                        break;
                    case 1:
                        inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        view.findViewById(R.id.background).setBackgroundResource(R.drawable.s0100bgi);
                        setContentView(view);
                        background = (RelativeLayout) findViewById(R.id.background);
                        leftChar = (ImageView) findViewById(R.id.leftChar);
                        rightChar = (ImageView) findViewById(R.id.rightChar);
                        charLines = (TextView) findViewById(R.id.lines);
                        Animation fadeOut2 = new AlphaAnimation(1, 0);
                        fadeOut2.setInterpolator(new AccelerateInterpolator()); //and this
                        fadeOut2.setStartOffset(1000);
                        fadeOut2.setDuration(1000);
                        fadeOut2.setRepeatCount(0);
                        background.startAnimation(fadeOut2);
                        charLines.setBackgroundResource(R.drawable.mary);
                        typing("嗯⋯⋯", 500);
                        background.setOnClickListener(Scenes.this);
                        leftChar.setOnClickListener(Scenes.this);
                        rightChar.setOnClickListener(Scenes.this);
                        charLines.setOnClickListener(Scenes.this);
                        break;
                    case 2:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        typing("為什麼我會在這裏的？", 300);
                        break;
                    case 3:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100);
                        typing("救我⋯⋯", 500);
                        break;
                    case 4:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0101bgi);
                        typing("「咚，咚，咚⋯⋯」（此時指針指向12）", 400);
                        break;
                    case 5:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100bgi);
                        rightChar.setImageResource(R.drawable.rightchar01);
                        typing("終於可以動了，但為什麼會這樣？", 200);
                        break;
                    case 6:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        typing("你是新的受害者呢......(突然聽到一把聲音)", 200);

                        break;
                    case 7:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_right_mary();
                        typing("你⋯⋯是誰？", 200);
                        break;
                    case 8:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        typing("你好，我叫彼得。按你身上的名片，你叫瑪莉？", 200);
                        break;
                    case 9:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_right_mary();
                        rightChar.setImageResource(R.drawable.rightchar04);
                        typing("剛剛你說什麼「新的受害者」？還有為什麼我會在這裡的？", 200);
                        break;
                    case 10:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        typing("看來你什麼都不知道呢⋯⋯", 200);
                        break;
                    default:
                        skipTyping();
                        shotNum = 0;
                        scenesNum++;
                        break;
                }
                break;
            case 1:
                switch (shotNum) {
                    case 0:
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        findViewById(R.id.background).setOnClickListener(Scenes.this);
                        findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                        findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setHeight(800);
                        typing("第二章\n\t第一個朋友", 300);
                        break;
                    case 1:
                        if (takeNum == 0) {
                            inflater = (LayoutInflater) getApplicationContext().getSystemService
                                    (Context.LAYOUT_INFLATER_SERVICE);
                            view = inflater.inflate(R.layout.activity_scene_base, null);
                            view.findViewById(R.id.background).setBackgroundResource(R.drawable.s0100bgi);
                            findViewById(R.id.background).startAnimation(fadeOut);
                            setContentView(view);
                            background = (RelativeLayout) findViewById(R.id.background);
                            leftChar = (ImageView) findViewById(R.id.leftChar);
                            rightChar = (ImageView) findViewById(R.id.rightChar);
                            charLines = (TextView) findViewById(R.id.lines);
                            turn_left_peter();
                            typing("看來你什麼都不知道呢⋯⋯", 200);
                            background.setOnClickListener(Scenes.this);
                            leftChar.setOnClickListener(Scenes.this);
                            rightChar.setOnClickListener(Scenes.this);
                            charLines.setOnClickListener(Scenes.this);
                        } else {
                            skipTyping();
                        }
                        break;
                    case 2:
                        if (takeNum == 0) {
                            turn_right_mary();
                            typing("什麼？", 100);
                        } else {
                            skipTyping();
                        }
                        break;
                    case 3:
                        if (takeNum == 0) {
                            turn_left_peter();
                            typing("你知道嗎⋯⋯這裹的人偶都是用人類造成的。", 200);
                        } else {
                            skipTyping();
                        }
                        break;
                    case 4:
                        if (takeNum == 0) {
                            turn_right_mary();
                            rightChar.setImageResource(R.drawable.rightchar03);
                            typing("⋯⋯騙人，你怎知道？", 300);
                        } else {
                            skipTyping();
                        }
                        break;
                    case 5:
                        if (takeNum == 0) {
                            turn_left_peter();
                            rightChar.setImageResource(R.drawable.rightchar03);
                            charLines.setPadding(80, 30, 80, 30);
                            typing("昨天我看到一個女孩，和你一模一樣的，被店主捉了，然後今天我便看到你在這裏了。這裏的人偶也一樣，你說這會是巧合麼？", 200);
                        } else {
                            skipTyping();
                        }
                        break;
                    case 6:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setPadding(80, 80, 80, 80);
                        turn_right_mary();
                        rightChar.setImageResource(R.drawable.rightchar03);
                        typing("怎麼會是這樣！我是否永遠變不回去？", 200);
                        break;
                    case 7:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        rightChar.setImageResource(R.drawable.rightchar03);
                        charLines.setPadding(80, 30, 80, 30);
                        typing("這裡的每一個人偶都有屬於自己的故事，十個？二十個？我都忘記至今受害者的人數，不過從未有人能解除身上的詛咒。而你......會成為改變命運的第一人嗎？", 150);
                        break;
                    case 8:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setPadding(80, 80, 80, 80);
                        turn_right_mary();
                        rightChar.setImageResource(R.drawable.rightchar04);
                        typing("我想嘗試一下......", 200);
                        break;
                    case 9:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        charLines.setPadding(80, 30, 80, 30);
                        typing("那我給你一些提示吧。你每一天只有三小時可以活動。我在尋找離開方法時，曾在收銀處前發現一些資料，這可能協助你離開這裡。", 100);
                        break;
                    default:
                        skipTyping();
                        scenesNum++;
                        shotNum = 0;
                        break;
                }
                break;
            case 2:
                switch (shotNum) {
                    case 0:
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        findViewById(R.id.background).setOnClickListener(Scenes.this);
                        findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                        findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setHeight(800);
                        typing("第三章\n\t零碎的回憶", 300);
                        break;
                    case 1:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundResource(R.drawable.s0300bgi);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        background = (RelativeLayout) findViewById(R.id.background);
                        leftChar = (ImageView) findViewById(R.id.leftChar);
                        rightChar = (ImageView) findViewById(R.id.rightChar);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setBackgroundResource(R.drawable.nline);
                        typing("（二人來到收銀處前。）", 200);
                        background.setOnClickListener(Scenes.this);
                        leftChar.setOnClickListener(Scenes.this);
                        rightChar.setOnClickListener(Scenes.this);
                        charLines.setOnClickListener(Scenes.this);
                        break;
                    case 2:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0301bgi);
                        charLines.setBackgroundResource(R.drawable.mary);
                        typing("這本筆記是你所指的資料？", 200);
                        break;
                    case 3:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setBackgroundResource(R.drawable.peter);
                        typing("對，我在幾天前發現的，不知道有沒有離開的線索......", 200);
                        break;
                    case 4:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0302bgi);
                        charLines.setBackgroundResource(R.drawable.mary);
                        charLines.setPadding(80, 80, 40, 60);
                        typing("原來是商店的資料。這裡寫「業主﹕德比」你知道這個人嗎？", 200);
                        break;
                    case 5:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setBackgroundResource(R.drawable.peter);
                        charLines.setPadding(80, 80, 80, 80);
                        typing("他是把我們變成人偶的人......", 400);
                        break;
                    case 6:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100bgi);
                        turn_right_mary();
                        rightChar.setImageResource(R.drawable.rightchar03);
                        typing("那我們最後要將他打敗，才能離開？", 200);
                        break;
                    case 7:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_left_peter();
                        rightChar.setImageResource(R.drawable.rightchar03);
                        typing(".......不知道.....", 400);
                        break;
                    case 8:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        turn_right_mary();
                        rightChar.setImageResource(R.drawable.rightchar03);
                        typing("............", 400);
                        break;
                    case 9:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundResource(R.drawable.s0303bgi);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        background = (RelativeLayout) findViewById(R.id.background);
                        leftChar = (ImageView) findViewById(R.id.leftChar);
                        rightChar = (ImageView) findViewById(R.id.rightChar);
                        charLines = (TextView) findViewById(R.id.lines);
                        background.setOnClickListener(Scenes.this);
                        leftChar.setOnClickListener(Scenes.this);
                        rightChar.setOnClickListener(Scenes.this);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setPadding(80, 80, 60, 60);
                        charLines.setBackgroundResource(R.drawable.mary);
                        typing("你看，這頁是我的購入記錄......咦？怎麼後面被撕去了一頁？", 200);
                        break;
                    case 10:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setBackgroundResource(R.drawable.peter);
                        typing("那頁寫的是身世⋯⋯所有人偶的身世全被人故意撕下了。", 250);
                        break;
                    case 11:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        charLines.setBackgroundResource(R.drawable.mary);
                        typing("身世會包含事情的真相嗎？", 300);
                        break;
                    case 12:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100bgi);
                        turn_left_peter();
                        typing("不管如何，去找找吧。我想你應該不了解店裏的結構，讓我來當嚮導吧！", 300);
                        break;
                    case 13:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100);
                        charLines.setBackgroundResource(R.drawable.mary);
                        leftChar.setImageDrawable(null);
                        rightChar.setImageDrawable(null);
                        typing("（這股熟悉感，我好像曾經到過這家店來，甚至不止一次？）", 150);
                        break;
                    case 14:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        background.setBackgroundResource(R.drawable.s0100bgi);
                        charLines.setBackgroundResource(R.drawable.nline);
                        charLines.setHeight(800);
                        SpannableString ss = new SpannableString("選項：\nA.-木櫃\n\nB.-倉庫\n\nC.-櫥窗");
                        ss.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View textView) {
                                choice = 1;
                                scenesNum++;
                                shotNum = 0;
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setUnderlineText(false);
                            }
                        }, 4, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        ss.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View textView) {
                                choice = 2;
                                scenesNum++;
                                shotNum = 0;
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setUnderlineText(false);
                            }
                        }, 11, 16, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        ss.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View textView) {
                                choice = 3;
                                scenesNum++;
                                shotNum = 0;
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setUnderlineText(false);
                            }
                        }, 18, 23, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        charLines.setPadding(80, 110, 60, 60);
                        charLines.setText(ss);
                        charLines.setMovementMethod(LinkMovementMethod.getInstance());
                        charLines.setHighlightColor(Color.TRANSPARENT);
                        break;
                    default:
                        skipTyping();
                        break;
                }
                break;
            case 3:
                switch (shotNum) {
                    case 0:
                        autoPlayStr = new LinkedList<>();
                        autoPlayImg = new LinkedList<>();
                        switch (choice) {
                            case 1:
                                autoPlayImg.offer(new int[]{R.drawable.s0400bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0401bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, 0, R.drawable.rightchar01});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, 0});
                                autoPlayStr.offer("這櫃放的都是老古董人偶，看起來帶點古雅呢！");
                                autoPlayStr.offer("這兒......（突然眼前冒出一顆紅色圓形物件）啊！");
                                autoPlayStr.offer("怎麼了？");
                                autoPlayStr.offer("沒什麼，找找筆記，我總覺得我們的身世好像在這個地方。");
                                autoPlayStr.offer("這三個人偶都好像有點怪怪的，搞不好其中一個就藏着我們的身世！");
                                break;
                            case 2:
                                autoPlayImg.offer(new int[]{R.drawable.s0402bgi, R.drawable.nline, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0403bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0403bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0404bgi, R.drawable.mary, 0, 0});
                                autoPlayStr.offer("上了鎖的倉庫");
                                autoPlayStr.offer("這道門開不了.....");
                                autoPlayStr.offer("我們先去找其他地方吧，說不定其他地方會有這門的鎖匙。");
                                autoPlayStr.offer("啊！（回想起奇怪的記憶）");
                                break;
                            case 3:
                                autoPlayImg.offer(new int[]{R.drawable.s0405bgi, R.drawable.nline, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0405bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, 0, R.drawable.rightchar04});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, R.drawable.leftchar01, R.drawable.rightchar04});
                                autoPlayStr.offer("（二人來到櫥窗前）");
                                autoPlayStr.offer("這裏放的都是最矚目的暢銷人偶。你可以憑記憶想起什麼暗格嗎？可能安娜就藏在這兒也說不定。");
                                autoPlayStr.offer("啊！櫥窗最頂層那個綠色箭頭標記的盒子！是我小時候用來放人偶衣服的！");
                                autoPlayStr.offer("它的位置太高了，我們要取點工具幫忙。");
                                break;
                            default:
                                break;
                        }
                        String[] LastChoices = {"木櫃", "倉庫", "櫥窗"};
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        findViewById(R.id.background).setOnClickListener(Scenes.this);
                        findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                        findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setHeight(800);
                        typing("前往...\n\t" + LastChoices[choice - 1], 300);
                        break;
                    case 1:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        shotNum++;
                        onClick(v);
                        break;
                    case 2:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        switch (choice) {
                            case 1:
                                autoPlay(this.findViewById(android.R.id.content).getRootView());
                                charLines.setPadding(80, 80, 40, 60);
                                break;
                            case 2:
                                autoPlay(this.findViewById(android.R.id.content).getRootView());
                                charLines.setPadding(80, 80, 40, 60);
                                break;
                            case 3:
                                autoPlay(this.findViewById(android.R.id.content).getRootView());
                                charLines.setPadding(80, 80, 40, 60);

                                if (autoPlayStr.size() == 0) {
                                    rightChar.setImageAlpha(175);
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        scenesNum++;
                        shotNum = 0;
                        onClick(v);
                        /*if (tries < 3) {
                            //bad end
                            //bad_ending_choice(view);
                            //goto choice
                            //scenesNum = 2; shotNum = 14;
                            break;
                        }*/
                        break;
                }
                break;
            case 4:
                switch (shotNum) {
                    case 0:
                        if (takeNum == 1) {
                            skipTyping();
                            break;
                        }
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        shotNum = choice;
                        background = (RelativeLayout) view.findViewById(R.id.background);
                        leftChar = (ImageView) view.findViewById(R.id.leftChar);
                        rightChar = (ImageView) view.findViewById(R.id.rightChar);
                        charLines = (TextView) view.findViewById(R.id.lines);
                        onClick(v);
                        break;
                    case 1:
                        background.setBackgroundResource(R.drawable.s0400bgi);
                        LinearLayout opts = (LinearLayout) background.getChildAt(0);
                        ImageView imageView = new ImageView(Scenes.this);
                        imageView.setImageResource(R.drawable.opt2char2);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                        opts.addView(imageView, 1);
                        leftChar.setImageResource(R.drawable.opt2char1);
                        rightChar.setImageResource(R.drawable.opt2char3);
                        charLines.setText("是那隻隱藏了秘密？");
                        leftChar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.leftChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.rightChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines.setHeight(800);
                                typing("這只是一個已死去的普通人偶...沒有甚麼特別...斃了，店主來了！", 300);
                            }
                        });
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.leftChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.rightChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines.setHeight(800);
                                typing("這只是一個已死去的普通人偶...沒有甚麼特別...斃了，店主來了！", 300);
                            }
                        });
                        rightChar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(Scenes.this);
                                findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                                findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(Scenes.this);
                                charLines.setHeight(800);
                                typing("你選對了，就是這顆紅色的鈕...", 300);
                                scenesNum++;
                                shotNum = 0;
                            }
                        });
                        break;
                    case 2:
                        inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        ((TextView)findViewById(R.id.lines)).setText("回去…");
                        view = inflater.inflate(R.layout.activity_scene_base, null);
                        fadeOut.setStartOffset(1500);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        background = (RelativeLayout) findViewById(R.id.background);
                        leftChar = (ImageView) findViewById(R.id.leftChar);
                        rightChar = (ImageView) findViewById(R.id.rightChar);
                        charLines = (TextView) findViewById(R.id.lines);
                        background.setOnClickListener(Scenes.this);
                        leftChar.setOnClickListener(Scenes.this);
                        rightChar.setOnClickListener(Scenes.this);
                        charLines.setOnClickListener(Scenes.this);
                        fadeOut.setStartOffset(0);
                        //goto choice
                        scenesNum = 2;
                        shotNum = 14;
                        takeNum = 0;
                        onClick(v);
                        break;
                    case 3:
                        background.setBackgroundResource(R.drawable.s0405bgi);
                        opts = (LinearLayout) background.getChildAt(0);
                        imageView = new ImageView(Scenes.this);
                        imageView.setImageResource(R.drawable.opt2char2);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                        opts.addView(imageView, 1);
                        leftChar.setImageResource(R.drawable.opt2char1);
                        rightChar.setImageResource(R.drawable.opt2char3);
                        charLines.setText("選擇哪一個工具可拿到盒子？");
                        leftChar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.leftChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.rightChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines.setHeight(800);
                                typing("這工具不足以取得綠箱子呢...斃了，店主來了！", 300);
                            }
                        });
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(Scenes.this);
                                findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                                findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(Scenes.this);
                                charLines.setHeight(800);
                                typing("正確答案！請繼續接受任務！", 300);
                                scenesNum++;
                                shotNum = 0;
                            }
                        });
                        rightChar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                        (Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.activity_scene_base, null);
                                view.findViewById(R.id.background).setBackgroundColor(Color.parseColor("#000000"));
                                findViewById(R.id.background).startAnimation(fadeOut);
                                setContentView(view);
                                findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.leftChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                findViewById(R.id.rightChar).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines = (TextView) findViewById(R.id.lines);
                                charLines.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (takeNum == 1) {
                                            skipTyping();
                                        }else {
                                            bad_ending_choice(BAD_END_1);
                                        }
                                    }
                                });
                                charLines.setHeight(800);
                                typing("這工具不足以取得綠箱子呢...斃了，店主來了！", 300);
                            }
                        });
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                if (takeNum == 1) {
                    skipTyping();
                    break;
                }
                switch (shotNum){
                    case 0:
                        autoPlayStr = new LinkedList<>();
                        autoPlayImg = new LinkedList<>();
                        charLines.setText("");
                        charLines.setPadding(80, 60, 40, 40);
                        charLines.setHeight(400);
                        switch (choice){
                            case 1:
                                autoPlayImg.offer(new int[]{R.drawable.s0400bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0601bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0601bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0601bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, 0, R.drawable.rightchar01});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, R.drawable.rightchar02});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, R.drawable.leftchar02, R.drawable.rightchar01});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, R.drawable.rightchar02});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, R.drawable.leftchar02, R.drawable.rightchar01});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, R.drawable.rightchar02});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, R.drawable.leftchar02, R.drawable.rightchar03});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, R.drawable.rightchar02});
                                autoPlayStr.offer("這個人偶身上的鈕扣和剛才在我腦裏閃過的一模一樣，看！這兒有一頁紙。");
                                autoPlayStr.offer("「瑪莉本為娃娃店的太子女，擁有地位崇高的父母，在別人的阿諛奉承之中並沒有任何交心的朋友，娃娃店是她的小樂園，而人偶是她唯一的玩伴。但是瑪莉因為家庭的壓力和恃寵生驕的性格，開始把人偶由玩伴變為發洩對象。」");
                                autoPlayStr.offer("娃娃店......會是這間嗎？難怪這兒對我竟有種說不出來的熟悉。");
                                autoPlayStr.offer("這間店真的是你的家族生意嗎？為什麼現在卻成了德比的煉獄？");
                                autoPlayStr.offer("噢，我想起來了，一年前一個男人提出接手經營這店，而我的爸媽不知為什麼答應了。說來奇怪，聽說這個男人開始經營娃娃店後，生意竟比之前更好，不過我從沒見過他，也已好幾年沒來這兒了。");
                                autoPlayStr.offer("呃，我可以向你提出一個請求嗎，瑪莉？");
                                autoPlayStr.offer("什麼事呢？");
                                autoPlayStr.offer("也許你現在就可以離開了，但你可以繼續留下替我找尋我的身世，以及安娜嗎？");
                                autoPlayStr.offer("安娜是誰？");
                                autoPlayStr.offer("她是繼我進來後的第二個受害者，是我在這兒的第一個朋友。一星期前的一晚，她說到收銀處查探一下，卻再沒回來了，我到處都找不到她，只找到那本可疑的筆記⋯⋯我實在很擔心她。");
                                autoPlayStr.offer("但在這個鬼地方多留一分一秒隨時身陷險境......");
                                autoPlayStr.offer("我明白，所以我也不想強人所難。你自己決定吧。");
                                break;
                            case 3:
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, 0, R.drawable.rightchar04});
                                autoPlayImg.offer(new int[]{R.drawable.s0600bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0600bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0600bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0600bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.peter, R.drawable.leftchar01, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0100bgi, R.drawable.mary, R.drawable.leftchar02, R.drawable.rightchar01});
                                autoPlayImg.offer(new int[]{R.drawable.s0602bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.tobecontinued, 0, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0602bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0603bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0604bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0606bgi, R.drawable.peter, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0607bgi, R.drawable.mary, 0, 0});
                                autoPlayImg.offer(new int[]{R.drawable.s0605bgi, R.drawable.mary, 0, 0});
                                autoPlayStr.offer("幸好繩子比長尺長，也不像人字梯那樣笨重和發出聲響，否則便可能被發現了。");
                                autoPlayStr.offer("（打開盒子）這是我的身世啊！");
                                autoPlayStr.offer("「彼得幼時愛玩玩偶，因而為人取笑，後來受朋友影響迷上了電玩。逐漸長大的彼得，開始認為男生不該玩人偶，遂將家中甚至從前最鍾愛的那個人偶都全部丟棄。」");
                                autoPlayStr.offer("我是這樣貪新忘舊的人嗎？");
                                autoPlayStr.offer("如果我是那個被打入冷宮的人偶，一定會心生不忿呢！\n（彼得白她一眼）\n開玩笑啦！");
                                autoPlayStr.offer("不好笑嘛。咦，原來盒子內還有一把鑰匙！");
                                autoPlayStr.offer("這......啊！是倉庫的鑰匙！");
                                autoPlayStr.offer("快去倉庫！");
                                autoPlayStr.offer("開啟的倉庫");
                                autoPlayStr.offer("記得你的鑰匙嗎？我們去倉庫。快用它來開倉庫的門");
                                autoPlayStr.offer("想不到這裏一個人偶也沒有啊。");
                                autoPlayStr.offer("這裏好像特別陰森恐怖啊！我們是不是不該來這裏呢？");
                                autoPlayStr.offer("還是快找找提示吧，我們時間不多了。");
                                autoPlayStr.offer("這裏有五件物品啊，搞不好其中一件就藏着安娜的線索！");
                                autoPlayStr.offer("啊！（回想起奇怪的回憶）");
                                break;
                            default:
                                break;
                        }
                        shotNum++;
                        onClick(v);
                        break;
                    case 1:
                        autoPlay(this.findViewById(android.R.id.content).getRootView());

                        if (choice == 1) {
                            switch (autoPlayStr.size()) {
                                case 0:
                                    rightChar.setImageAlpha(175);
                                    leftChar.setImageAlpha(255);
                                    break;
                                case 1:
                                    rightChar.setImageAlpha(255);
                                    leftChar.setImageAlpha(175);
                                    break;
                                case 2:
                                    rightChar.setImageAlpha(175);
                                    leftChar.setImageAlpha(255);
                                    break;
                                case 3:
                                    rightChar.setImageAlpha(255);
                                    leftChar.setImageAlpha(175);
                                    break;
                                case 4:
                                    rightChar.setImageAlpha(175);
                                    leftChar.setImageAlpha(255);
                                    break;
                                case 5:
                                    rightChar.setImageAlpha(255);
                                    leftChar.setImageAlpha(175);
                                    break;
                                case 6:
                                    rightChar.setImageAlpha(175);
                                    leftChar.setImageAlpha(255);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    default:
                        if (takeNum == 1) {
                            skipTyping();
                        }
                        scenesNum++;
                        shotNum = 0;
                        break;
                }
                break;
            case 6:
                if (takeNum == 1) {
                    skipTyping();
                    break;
                }
                switch (shotNum){
                    case 0:
                        shotNum++;
                        if (choice == 3){
                            shotNum++;
                        }
                        onClick(v);
                        break;
                    case 1:
                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                                (Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_scene_base, null);
                        view.findViewById(R.id.background).setBackgroundResource(R.drawable.s0100bgi);
                        findViewById(R.id.background).startAnimation(fadeOut);
                        setContentView(view);
                        findViewById(R.id.background).setOnClickListener(Scenes.this);
                        findViewById(R.id.leftChar).setOnClickListener(Scenes.this);
                        findViewById(R.id.rightChar).setOnClickListener(Scenes.this);
                        charLines = (TextView) findViewById(R.id.lines);
                        charLines.setOnClickListener(Scenes.this);
                        charLines.setBackgroundColor(Color.parseColor("#88FFFFFF"));
                        typing("你要怎樣做呢？", 200);
                        scenesNum++;
                        shotNum = 0;
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            default:
                if (takeNum == 1) {
                    skipTyping();
                    break;
                }
                break;
        }
        //startActivity(new Intent(this, scene_1_1.class));
        //finish();
    }

    private void bad_ending_choice(int badEndScene) {
        if (takeNum == 1) {
            skipTyping();
            fadeOut.setStartOffset(1000);
        }
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_scene_base, null);
        findViewById(R.id.background).startAnimation(fadeOut);
        setContentView(view);
        charLines = (TextView) findViewById(R.id.lines);
        charLines.setHeight(800);
        charLines.setTextColor(Color.parseColor("#000000"));
        charLines.setBackgroundColor(Color.parseColor("#88FFFFFF"));
        switch (badEndScene) {
            case BAD_END_1:
                view.findViewById(R.id.background).setBackgroundResource(R.drawable.ed00);
                typing("BAD END 1-擦肩而過的真相\n\n瑪莉和彼得在尋找隱藏自己身世物件時逃脫失敗，被店主發現，瑪莉被強行放進回收箱，從此再沒見過彼得。她十分害怕，雖帶着自己是人的回憶，卻知道自己永遠動彈不得了。她似乎明白了甚麼，但只能無奈並四肢無力地等待將臨的「處置」。而店主帶着陰險的微笑尋找新的人偶。", 150);
                break;
            default:
                break;
        }
        findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takeNum == 1) {
                    skipTyping();
                }else {
                    Scenes.this.recreate();
                }
            }
        });
        findViewById(R.id.leftChar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takeNum == 1) {
                    skipTyping();
                }else {
                    Scenes.this.recreate();
                }
            }
        });
        findViewById(R.id.rightChar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takeNum == 1) {
                    skipTyping();
                }else {
                    Scenes.this.recreate();
                }
            }
        });
        charLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takeNum == 1) {
                    skipTyping();
                }else {
                    Scenes.this.recreate();
                }
            }
        });
    }

    private void autoPlay(View view) {
        background = (RelativeLayout) view.findViewById(R.id.background);
        leftChar = (ImageView) view.findViewById(R.id.leftChar);
        rightChar = (ImageView) view.findViewById(R.id.rightChar);
        charLines = (TextView) view.findViewById(R.id.lines);
        if (autoPlayStr.size() != 0) {
            int[] screen = autoPlayImg.poll();
            background.setBackgroundResource(screen[0]);
            charLines.setBackgroundResource(screen[1]);
            leftChar.setImageResource(screen[2]);
            rightChar.setImageResource(screen[3]);
            typing(autoPlayStr.poll(), 200);
            shotNum--;
            if (autoPlayStr.peek() == null) {
                tries++;
                shotNum++;
            }
        } else {
            skipTyping();
        }
        background.setOnClickListener(Scenes.this);
        leftChar.setOnClickListener(Scenes.this);
        rightChar.setOnClickListener(Scenes.this);
        charLines.setOnClickListener(Scenes.this);
    }

    private void turn_left_peter() {
        leftChar.setImageResource(R.drawable.leftchar01);
        rightChar.setImageResource(R.drawable.rightchar02);
        leftChar.setImageAlpha(255);
        rightChar.setImageAlpha(175);
        charLines.setBackgroundResource(R.drawable.peter);
    }

    private void turn_right_mary() {
        leftChar.setImageResource(R.drawable.leftchar02);
        rightChar.setImageResource(R.drawable.rightchar01);
        leftChar.setImageAlpha(175);
        rightChar.setImageAlpha(255);
        charLines.setBackgroundResource(R.drawable.mary);
    }

    private void typing(final String text, final long delay) {
        charLines.setText("");
        shotNum++;
        takeNum = 1;
        currLine = text;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                String s = "";
                for (int i = text.length(); i != 0 && text.equals(currLine); i--) {
                    s += text.charAt(text.length() - i);
                    Message m = new Message();
                    m.what = 0;
                    m.obj = s;
                    mHandler.sendMessage(m);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                    if (s.equals(currLine)) {
                        takeNum = 0;
                        break;
                    }
                }
            }
        });
        if (t.isAlive()) {
            t.interrupt();
        }
        t.start();
    }

    private void skipTyping() {
        Message m = new Message();
        m.what = 0;
        m.obj = currLine;
        currLine = "";
        if (t.isAlive()) {
            t.interrupt();
        }
        mHandler.sendMessage(m);
        takeNum = 0;
    }
}
